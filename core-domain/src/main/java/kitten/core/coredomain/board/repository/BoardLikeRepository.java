package kitten.core.coredomain.board.repository;

import kitten.core.coredomain.board.entity.Board;
import kitten.core.coredomain.board.entity.BoardLike;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BoardLikeRepository extends CrudRepository<BoardLike, Long> {

    Optional<BoardLike> findByBoard(Board board);
}
