package kitten.core.coredomain.board.repository;

import kitten.core.coredomain.board.entity.Board;
import kitten.core.coredomain.board.entity.BoardImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BoardImageRepository extends CrudRepository<BoardImage, Long> {

    Optional<BoardImage> findByBoardAndRepresentativeIsTrue(Board board);

    List<BoardImage> findAllByBoard_Key(Long boardKey);
}
