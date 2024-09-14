package kitten.core.coredomain.user.repository;

import kitten.core.coredomain.user.entity.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users, Long> {

    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);

    boolean existsByNickName(String email);
}
