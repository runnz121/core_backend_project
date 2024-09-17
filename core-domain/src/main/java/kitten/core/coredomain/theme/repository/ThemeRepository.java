package kitten.core.coredomain.theme.repository;

import kitten.core.coredomain.theme.consts.ThemeType;
import kitten.core.coredomain.theme.entity.Theme;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ThemeRepository extends CrudRepository<Theme, Long> {

    Optional<Theme> findByType(ThemeType type);
}
