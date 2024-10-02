package kitten.core.coredomain.board.repository;

import kitten.core.coredomain.board.entity.Board;
import kitten.core.coredomain.user.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends CrudRepository<Board, Long> {

    Optional<Board> findByKeyAndDeletedIsFalse(Long boardKey);

    List<Board> findByUser(Users users);

    Page<Board> findAllByUser(Users users, PageRequest pageRequest);
}
