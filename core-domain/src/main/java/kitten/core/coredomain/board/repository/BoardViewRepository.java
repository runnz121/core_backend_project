package kitten.core.coredomain.board.repository;

import kitten.core.coredomain.board.entity.Board;
import kitten.core.coredomain.board.entity.BoardView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardViewRepository extends CrudRepository<BoardView, Long> {

    List<BoardView> findByBoard(Board board);
}
