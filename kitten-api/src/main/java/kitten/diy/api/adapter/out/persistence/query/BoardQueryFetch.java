package kitten.diy.api.adapter.out.persistence.query;

import kitten.diy.api.adapter.out.model.BoardQueryData;
import kitten.diy.api.application.port.in.command.command.BoardInfoSearchCommand;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardQueryFetch {

    Page<BoardQueryData> getMainHomeDatas(BoardInfoSearchCommand command);

    List<String> getTagLikes(TagLikeSearchCommand command);
}
