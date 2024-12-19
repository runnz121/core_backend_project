package kitten.core.coredomain.board.repository;

import kitten.core.coredomain.board.entity.BoardItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BoardItemRepository extends CrudRepository<BoardItem, Long> {

    Optional<BoardItem> findByBoard_KeyAndDeletedIsFalse(Long boardKey);
}
