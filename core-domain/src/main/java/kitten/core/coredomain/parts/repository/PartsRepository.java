package kitten.core.coredomain.parts.repository;

import kitten.core.coredomain.parts.entity.Parts;
import org.springframework.data.repository.CrudRepository;

public interface PartsRepository extends CrudRepository<Parts, Long> {
}
