package kitten.diy.api.application.port.in.query;

import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.application.port.in.command.command.BoardInfoSearchCommand;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import kitten.diy.api.application.port.in.query.data.BoardDetailData;
import kitten.diy.api.application.port.in.query.data.BoardInfoData;
import kitten.diy.api.application.port.in.query.data.BoardLikeUsersData;
import kitten.diy.api.application.port.in.query.data.BoardPartsInfo;

import java.util.List;

public interface BoardQueryUseCase {

    PageableData<List<BoardInfoData>> getBoardInfos(BoardInfoSearchCommand command);

    BoardDetailData getDetailData(Long boardKey);

    List<BoardLikeUsersData> getBoardLikeUsers(Long boardKey);

    List<String> getLikeTags(TagLikeSearchCommand command);

    List<BoardPartsInfo> getPartsInfos(Long boardKey);

}
