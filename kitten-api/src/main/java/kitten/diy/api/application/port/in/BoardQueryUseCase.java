package kitten.diy.api.application.port.in;

import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.application.port.in.command.BoardInfoSearchCommand;
import kitten.diy.api.application.port.in.query.BoardDetailData;
import kitten.diy.api.application.port.in.query.BoardInfoData;

import java.util.List;

public interface BoardQueryUseCase {

    PageableData<List<BoardInfoData>> getBoardInfos(BoardInfoSearchCommand command);

    BoardDetailData getDetailData(Long boardKey);
}
