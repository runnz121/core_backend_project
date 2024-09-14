package kitten.core.coredomain.board.repository;

import kitten.core.coredomain.board.entity.Board;
import kitten.core.coredomain.board.entity.BoardTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardTagRepository extends CrudRepository<BoardTag, Long> {

    List<BoardTag> findByBoard(Board board);
}
