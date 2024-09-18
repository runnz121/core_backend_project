package kitten.core.coredomain.moru.repository;

import kitten.core.coredomain.moru.entity.MoruParts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MoruPartsRepository extends CrudRepository<MoruParts, Long> {

    List<MoruParts> findAllByParentKey(Long parentKey);

    Optional<MoruParts> findByKey(Long parentKey);
}
