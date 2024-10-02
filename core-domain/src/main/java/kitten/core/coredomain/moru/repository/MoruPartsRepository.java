package kitten.core.coredomain.moru.repository;

import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.moru.entity.MoruParts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MoruPartsRepository extends CrudRepository<MoruParts, Long> {

    List<MoruParts> findAllByParentKey(Long parentKey);

    @Description("파츠의 part key")
    Optional<MoruParts> findByKey(Long parentPartKey);

    @Description("파츠의 부모 key")
    Optional<MoruParts> findByParentKey(Long parentKey);

    List<MoruParts> findAllByParentKeyIsNull();
}
