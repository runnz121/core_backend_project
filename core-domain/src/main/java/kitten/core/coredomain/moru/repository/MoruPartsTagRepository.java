package kitten.core.coredomain.moru.repository;

import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.moru.entity.MoruPartsTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MoruPartsTagRepository extends CrudRepository<MoruPartsTag, Long> {

    List<MoruPartsTag> findAllByMoruParts(MoruParts moruParts);
}
