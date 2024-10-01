package kitten.core.coredomain.theme.repository;

import kitten.core.coredomain.theme.entity.Theme;
import kitten.core.coredomain.theme.entity.ThemeParts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThemePartsRepository extends CrudRepository<ThemeParts, Long> {

    List<ThemeParts> findAllByTheme(Theme theme);

    List<ThemeParts> findAllByParts_KeyIn(List<Long> partKeys);
}
