package kitten.diy.api.adapter.out.persistence;

import kitten.core.coredomain.terms.repository.TermsAgreementRepository;
import kitten.diy.api.application.port.out.TermsAgreementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TermsPersistenceAdapter implements TermsAgreementPort {

    private final TermsAgreementRepository termsAgreementRepository;

    @Override
    @Transactional
    public void saveUserTermsAgreement() {

        termsAgreementRepository.save(null);
    }
}
