package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.board.consts.BoardType;
import kitten.core.coredomain.board.entity.*;
import kitten.core.coredomain.board.repository.*;
import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.adapter.out.error.BoardErrorCode;
import kitten.diy.api.adapter.out.error.UserErrorCode;
import kitten.diy.api.application.port.in.command.command.AvatarCommand;
import kitten.diy.api.application.port.in.command.command.BoardLikeCommand;
import kitten.diy.api.application.port.out.BoardPersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BoardPersistenceAdapter implements BoardPersistentPort {

    private  final UsersRepository usersRepository;

    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardTagRepository boardTagRepository;
    private final BoardImageRepository boardImageRepository;
    private final BoardItemRepository boardItemRepository;

    @Override
    @Transactional
    public boolean likeBoard(BoardLikeCommand command) {
        boardLikeRepository.findByCreateByAndBoard_key(command.userName(), command.boardKey())
                .ifPresentOrElse(
                        existingLike -> {
                            boardLikeRepository.delete(existingLike);
                        },
                        () -> {
                            Board board = boardRepository.findByKeyAndDeletedIsFalse(command.boardKey())
                                    .orElseThrow(() -> new CommonRuntimeException(BoardErrorCode.BOARD_NOT_FOUND));

//                            Users user = getUsers(command.userName());

                            BoardLike newLike = BoardLike.builder()
                                    .board(board)
                                    .users(board.getUser()) // 게시글 작성한 유저 정보를 저장
                                    .build();

                            boardLikeRepository.save(newLike);
                        }
                );

        return boardLikeRepository.findByCreateByAndBoard_key(command.userName(), command.boardKey()).isPresent();
    }


    @Override
    @Transactional
    public void saveBoard(AvatarCommand command,
                          MoruUserArtInfo savedArtInfo) {

        Users user = getUsers(command.userName());

        Board board = Board.builder()
                .user(user)
                .comment(command.comment())
                .type(BoardType.MORU)
                .postStatus(command.postStatus())
                .comment(command.comment())
                .build();

        Board savedBoard = boardRepository.save(board);

        command.tags().forEach(
                tag -> {
                    BoardTag boardTag = BoardTag.builder()
                            .board(savedBoard)
                            .tag(tag)
                            .build();

                    boardTagRepository.save(boardTag);
                }
        );

        BoardImage boardImage = BoardImage.builder()
                .board(board)
                .imageUrl(command.repImgUrl())
                .representative(true)
                .sort(1)
                .build();

        boardImageRepository.save(boardImage);

        BoardItem boardItem = BoardItem.builder()
                .board(board)
                .userArtInfo(savedArtInfo)
                .build();

        boardItemRepository.save(boardItem);
    }

    @Override
    @Transactional
    public void deleteBoard(Long boardKey) {
        boardRepository.findByKeyAndDeletedIsFalse(boardKey).ifPresent(
                Board::deleteBoard
        );
    }

    @Transactional(readOnly = true)
    public Users getUsers(String userEmail) {
        return usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CommonRuntimeException(UserErrorCode.USER_NOT_EXISTS));
    }
}
