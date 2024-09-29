package kitten.core.coredomain.moru.repository;

import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
import kitten.core.coredomain.moru.entity.MoruUserPart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MoruUsePartRepository extends CrudRepository<MoruUserPart, Long> {

    List<MoruUserPart> findAllByMoruUserArtInfo(MoruUserArtInfo moruUserArtInfo);
}
