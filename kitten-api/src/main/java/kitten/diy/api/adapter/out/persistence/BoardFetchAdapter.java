package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.board.entity.*;
import kitten.core.coredomain.board.repository.*;
import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.moru.entity.MoruUserPart;
import kitten.core.coredomain.moru.repository.MoruUsePartRepository;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.adapter.out.error.BoardErrorCode;
import kitten.diy.api.adapter.out.model.BoardQueryData;
import kitten.diy.api.adapter.out.persistence.query.BoardQueryFetch;
import kitten.diy.api.application.port.in.command.command.BoardInfoSearchCommand;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import kitten.diy.api.application.port.in.query.data.BoardDetailData;
import kitten.diy.api.application.port.in.query.data.BoardLikeUsersData;
import kitten.diy.api.application.port.in.query.data.BoardPartsInfo;
import kitten.diy.api.application.port.out.BoardFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class BoardFetchAdapter implements BoardFetchPort {

    private final BoardRepository boardRepository;
    private final BoardViewRepository boardViewRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardTagRepository boardTagRepository;
    private final BoardImageRepository boardImageRepository;
    private final BoardItemRepository boardItemRepository;

    private final MoruUsePartRepository moruUsePartRepository;
    private final UsersRepository usersRepository;

    private final BoardQueryFetch boardQueryFetch;

    @Override
    @Transactional(readOnly = true)
    public Page<BoardQueryData> getBoardDatas(BoardInfoSearchCommand command) {
        return boardQueryFetch.getMainHomeDatas(command);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardDetailData getBoardDetail(Long boardKey) {
        Board board = boardRepository.findByKeyAndDeletedIsFalse(boardKey)
                .orElseThrow(() -> new CommonRuntimeException(BoardErrorCode.BOARD_NOT_FOUND));
        return BoardDetailData.of(
                board,
                getBoardImage(board),
                getLikeCounts(board),
                getViewCounts(board),
                getTags(board)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoardLikeUsersData> getBoardLikeUsers(Long boardKey) {
        Board board = boardRepository.findByKeyAndDeletedIsFalse(boardKey)
                .orElseThrow(() -> new CommonRuntimeException(BoardErrorCode.BOARD_NOT_FOUND));
        List<BoardLike> boardLikes = boardLikeRepository.findByBoard(board);
        List<Users> likeUsers = boardLikes.stream().map(BoardLike::getUsers).toList();
        return likeUsers.stream().map(user -> BoardLikeUsersData.of(user.getNickName(), user.getProfileImgUrl())).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getLikeTags(TagLikeSearchCommand command) {
        return boardQueryFetch.getTagLikes(command);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getTagsByBoardKey(Long boardKey) {
        return boardTagRepository
                .findByBoard_Key(boardKey).stream()
                .map(BoardTag::getTag)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoardPartsInfo> getBoardPartsInfos(Long boardKey) {
        Board board = boardRepository.findByKeyAndDeletedIsFalse(boardKey)
                .orElseThrow(() -> new CommonRuntimeException(BoardErrorCode.BOARD_NOT_FOUND));
        BoardItem boardItem = boardItemRepository.findByBoard_KeyAndDeletedIsFalse(boardKey)
                .orElseThrow(() -> new CommonRuntimeException(BoardErrorCode.BOARD_ITEM_NOT_FOUND));
        List<MoruUserPart> useMoruParts = moruUsePartRepository.findAllByMoruUserArtInfo(boardItem.getUserArtInfo());

        return Stream.ofNullable(useMoruParts)
                .flatMap(List::stream)
                .map(usePart -> {
                    MoruParts moruParts = usePart.getMoruParts();

                    BoardPartsInfo boardPartsInfo = BoardPartsInfo.builder()
                            .partsUserNickName(getUsersNickName(moruParts.getCreateBy()))
                            .partsName(moruParts.getName())
                            .width(moruParts.getWidth())
                            .height(moruParts.getHeight())
                            .colorHexCode(moruParts.getColorHexCode())
                            .partImgUrl(moruParts.getImageUrl())
                            .purchaseInfos(moruParts.getPurchaseInfos())
                            .tags(getTags(board))
                            .build();
                    return boardPartsInfo;
                })
                .toList();
    }

    private String getBoardImage(Board board) {
        return boardImageRepository.findByBoardAndRepresentativeIsTrue(board)
                .map(BoardImage::getImageUrl)
                .orElse(null);
    }

    private Integer getLikeCounts(Board board) {
        return boardLikeRepository.findByBoard(board)
                .size();
    }

    private Integer getViewCounts(Board board) {
        return boardViewRepository.findByBoard(board)
                .size();
    }

    private List<String> getTags(Board board) {
        return boardTagRepository
                .findByBoard(board).stream()
                .map(BoardTag::getTag)
                .toList();
    }

    private String getUsersNickName(String userEmail) {
        return usersRepository.findByEmail(userEmail)
                .map(Users::getNickName)
                .orElse("");
    }
}
