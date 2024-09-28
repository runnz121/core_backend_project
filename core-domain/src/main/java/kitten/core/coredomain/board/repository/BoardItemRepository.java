package kitten.core.coredomain.board.repository;

import kitten.core.coredomain.board.entity.BoardItem;
import org.springframework.data.repository.CrudRepository;

public interface BoardItemRepository extends CrudRepository<BoardItem, Long> {
}
