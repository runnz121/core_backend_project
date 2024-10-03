package kitten.core.coredomain.moru.repository;

import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
import kitten.core.coredomain.moru.entity.MoruUserPart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MoruUsePartRepository extends CrudRepository<MoruUserPart, Long> {

    List<MoruUserPart> findAllByMoruUserArtInfo(MoruUserArtInfo moruUserArtInfo);

    Optional<MoruUserPart> findByMoruPartsAndMoruUserArtInfo(MoruParts moruParts, MoruUserArtInfo moruUserArtInfo);
}
