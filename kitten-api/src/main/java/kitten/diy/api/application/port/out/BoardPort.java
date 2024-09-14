package kitten.diy.api.application.port.out;

import kitten.diy.api.adapter.out.model.BoardQueryData;
import kitten.diy.api.application.port.in.command.command.BoardInfoSearchCommand;
import kitten.diy.api.application.port.in.query.data.BoardDetailData;
import org.springframework.data.domain.Page;

public interface BoardPort {

    Page<BoardQueryData> getBoardDatas(BoardInfoSearchCommand command);

    BoardDetailData getBoardDetail(Long boardKey);
}
