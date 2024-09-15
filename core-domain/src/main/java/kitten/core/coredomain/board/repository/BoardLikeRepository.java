package kitten.core.coredomain.board.repository;

import kitten.core.coredomain.board.entity.Board;
import kitten.core.coredomain.board.entity.BoardLike;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardLikeRepository extends CrudRepository<BoardLike, Long> {

    List<BoardLike> findByBoard(Board board);
}
