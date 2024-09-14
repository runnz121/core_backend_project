package kitten.diy.api.adapter.out.persistence.query;

import kitten.diy.api.adapter.out.model.BoardQueryData;
import kitten.diy.api.application.port.in.command.command.BoardInfoSearchCommand;
import org.springframework.data.domain.Page;

public interface BoardQueryFetch {

    Page<BoardQueryData> getMainHomeDatas(BoardInfoSearchCommand command);
}
