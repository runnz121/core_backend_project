package kitten.core.coredomain.terms.repository;

import kitten.core.coredomain.terms.entity.TermsAgreement;
import org.springframework.data.repository.CrudRepository;

public interface TermsAgreementRepository extends CrudRepository<TermsAgreement, Long> {
}
