package kitten.core.coredomain.moru.repository;

import kitten.core.coredomain.moru.entity.Moru;
import kitten.core.coredomain.theme.entity.Theme;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MoruRepository extends CrudRepository<Moru, Long> {

    Optional<Moru> findByTheme(Theme theme);

    List<Moru> findAllByTheme(Theme theme);
}
