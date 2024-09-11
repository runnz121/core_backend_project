package kitten.diy.api.adapter.out.persistence;

import kitten.core.coredomain.terms.repository.TermsAgreementRepository;
import kitten.core.coredomain.terms.repository.TermsRepository;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.application.port.in.command.TermsAgreementCommand;
import kitten.diy.api.application.port.out.TermsAgreementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TermsPersistenceAdapter implements TermsAgreementPort {

    private final UsersRepository usersRepository;
    private final TermsRepository termsRepository;
    private final TermsAgreementRepository termsAgreementRepository;

    @Override
    @Transactional
    public void saveUserTermsAgreement(TermsAgreementCommand command) {
        usersRepository.findByEmail(command.userEmail())
                       .ifPresent(user -> saveTermsAgreement(command, user));
    }

    private void saveTermsAgreement(TermsAgreementCommand command,
                                    Users user) {
        command.agreementTermsKeys().forEach(
                termsKey -> termsRepository.findByKey(termsKey)
                        .ifPresent(terms -> termsAgreementRepository.save(command.toEntity(terms, user))));
    }
}
