package kitten.core.coredomain.terms.repository;

import kitten.core.coredomain.terms.entity.Terms;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface TermsRepository extends CrudRepository<Terms, Long> {

    Optional<Terms> findByKey(Long termsKey);
}
