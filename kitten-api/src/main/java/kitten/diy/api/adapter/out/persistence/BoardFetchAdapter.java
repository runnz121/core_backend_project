package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.board.entity.*;
import kitten.core.coredomain.board.repository.*;
import kitten.core.coredomain.user.entity.Users;
import kitten.diy.api.adapter.out.consts.BoardErrorCode;
import kitten.diy.api.adapter.out.model.BoardQueryData;
import kitten.diy.api.adapter.out.persistence.query.BoardQueryFetch;
import kitten.diy.api.application.port.in.command.command.BoardInfoSearchCommand;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import kitten.diy.api.application.port.in.query.data.BoardDetailData;
import kitten.diy.api.application.port.in.query.data.BoardLikeUsersData;
import kitten.diy.api.application.port.in.query.data.BoardPartsInfo;
import kitten.diy.api.application.port.out.BoardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardFetchAdapter implements BoardPort {

    private final BoardRepository boardRepository;
    private final BoardViewRepository boardViewRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardTagRepository boardTagRepository;
    private final BoardImageRepository boardImageRepository;

    private final BoardQueryFetch boardQueryFetch;

    @Override
    @Transactional(readOnly = true)
    public Page<BoardQueryData> getBoardDatas(BoardInfoSearchCommand command) {
        return boardQueryFetch.getMainHomeDatas(command);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardDetailData getBoardDetail(Long boardKey) {
        Board board = boardRepository.findByKey(boardKey)
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
        Board board = boardRepository.findByKey(boardKey)
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
    public List<BoardPartsInfo> getBoardPartsInfos(Long boardKey) {
        Board board = boardRepository.findByKey(boardKey)
                .orElseThrow(() -> new CommonRuntimeException(BoardErrorCode.BOARD_NOT_FOUND));
        return null;
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
                .map(BoardView::getViewCount)
                .orElse(0);
    }

    private List<String> getTags(Board board) {
        return boardTagRepository
                .findByBoard(board).stream()
                .map(BoardTag::getTag)
                .toList();
    }
}
