package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.board.entity.*;
import kitten.core.coredomain.board.repository.*;
import kitten.core.coredomain.moru.entity.Moru;
import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
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
import kitten.diy.api.application.port.in.query.data.MyArtDetailData;
import kitten.diy.api.application.port.out.BoardFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
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
    public BoardDetailData getBoardDetail(Long boardKey,
                                          String userEmail) {
        Board board = getBoard(boardKey);
        return BoardDetailData.of(
                board,
                getBoardImage(board),
                getLikeCounts(board),
                getViewCounts(board),
                getTags(board),
                getIsMyLike(board, userEmail)
        );
    }

    @Override
    @Transactional
    public Board getBoard(Long boardKey) {
        return boardRepository.findByKeyAndDeletedIsFalse(boardKey)
                .orElseThrow(() -> new CommonRuntimeException(BoardErrorCode.BOARD_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoardLikeUsersData> getBoardLikeUsers(Long boardKey) {
        Board board = getBoard(boardKey);
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
        Board board = getBoard(boardKey);
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

    @Override
    @Transactional(readOnly = true)
    public MyArtDetailData getMyArtDetail(Long boardKey,
                                          String userEmail) {
        String userNickName = getUsersNickName(userEmail);
        BoardItem boardItem = boardItemRepository.findByBoard_KeyAndDeletedIsFalse(boardKey)
                .orElseThrow(() -> new CommonRuntimeException(BoardErrorCode.BOARD_ITEM_NOT_FOUND));

        Board board = boardItem.getBoard();

        Moru moru = boardItem.getUserArtInfo().getMoru();
        MoruUserArtInfo moruUserArtInfo = boardItem.getUserArtInfo();

        List<MoruUserPart> useMoruParts = moruUsePartRepository.findAllByMoruUserArtInfo(moruUserArtInfo);

        List<MyArtDetailData.PartInfo> partInfos = useMoruParts.stream().map(part -> {
            return MyArtDetailData.PartInfo.builder()
                    .partKey(part.getMoruParts().getKey())
                    .corX(part.getCorX())
                    .corY(part.getCorY())
                    .corZ(part.getCorZ())
                    .rotation(part.getRotation())
                    .side(part.getSide())
                    .customWidth(part.getCustomWidth())
                    .customHeight(part.getCustomHeight())
                    .partsImgUrl(part.getMoruParts().getImageUrl())
                    .build();
                })
                .toList();

       return MyArtDetailData.builder()
                .itemKey(moru.getKey())
                .width(moru.getWidth())
                .height(moru.getHeight())
                .frontImgUrl(moru.getFrontImageUrl())
                .backImgUrl(moru.getBackImageUrl())
                .colorHexCode(moruUserArtInfo.getMoruColorHexCode())
                .tags(getTags(board))
                .comment(board.getComment())
                .partInfos(partInfos)
                .userName(userNickName)
                .postStatus(board.getPostStatus())
                .build();
    }

    private List<BoardImage> getBoardImage(Board board) {
        return boardImageRepository.findAllByBoard_Key(board.getKey());
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

    @Override
    public Boolean getIsMyLike(Board board,
                               String userEmail) {
        if (!StringUtils.hasText(userEmail)) {
            return false;
        }
        return usersRepository.findByEmail(userEmail)
                .flatMap(user -> boardLikeRepository.findByBoardAndUsers(board, user))
                .isPresent();
    }
}
