package kitten.core.coredomain.board.repository;

import kitten.core.coredomain.board.entity.Board;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BoardRepository extends CrudRepository<Board, Long> {

    Optional<Board> findByKey(Long boardKey);
}
