package kitten.diy.api.application.port.out;

import kitten.core.coredomain.board.entity.Board;
import kitten.diy.api.adapter.out.model.BoardQueryData;
import kitten.diy.api.application.port.in.command.command.BoardInfoSearchCommand;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import kitten.diy.api.application.port.in.query.data.BoardDetailData;
import kitten.diy.api.application.port.in.query.data.BoardLikeUsersData;
import kitten.diy.api.application.port.in.query.data.BoardPartsInfo;
import kitten.diy.api.application.port.in.query.data.MyArtDetailData;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardFetchPort {

    Page<BoardQueryData> getBoardDatas(BoardInfoSearchCommand command);

    BoardDetailData getBoardDetail(Long boardKey, String userEmail);

    List<BoardLikeUsersData> getBoardLikeUsers(Long boardKey);

    List<String> getLikeTags(TagLikeSearchCommand command);

    List<String> getTagsByBoardKey(Long boardKey);

    List<BoardPartsInfo> getBoardPartsInfos(Long boardKey);

    MyArtDetailData getMyArtDetail(Long boardKey, String userEmail);

    Boolean getIsMyLike(Board board, String userEmail);

    Board getBoard(Long boardKey);
}
