package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.board.entity.Board;
import kitten.core.coredomain.board.entity.BoardLike;
import kitten.core.coredomain.board.repository.BoardLikeRepository;
import kitten.core.coredomain.board.repository.BoardRepository;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.adapter.out.error.BoardErrorCode;
import kitten.diy.api.adapter.out.error.UserErrorCode;
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

    @Override
    @Transactional
    public boolean likeBoard(BoardLikeCommand command) {
        boardLikeRepository.findByCreateByAndBoard_key(command.userName(), command.boardKey())
                .ifPresentOrElse(
                        existingLike -> {
                            boardLikeRepository.delete(existingLike);
                        },
                        () -> {
                            Board board = boardRepository.findByKey(command.boardKey())
                                    .orElseThrow(() -> new CommonRuntimeException(BoardErrorCode.BOARD_NOT_FOUND));

                            Users user = usersRepository.findByEmail(command.userName())
                                    .orElseThrow(() -> new CommonRuntimeException(UserErrorCode.USER_NOT_EXISTS));

                            BoardLike newLike = BoardLike.builder()
                                    .board(board)
                                    .users(user)
                                    .build();

                            boardLikeRepository.save(newLike);
                        }
                );

        return boardLikeRepository.findByCreateByAndBoard_key(command.userName(), command.boardKey()).isPresent();
    }
}
