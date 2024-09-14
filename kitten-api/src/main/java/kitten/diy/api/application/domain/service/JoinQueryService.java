package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.command.command.ValidateValueCommand;
import kitten.diy.api.application.port.in.query.JoinQueryUseCase;
import kitten.diy.api.application.port.out.JoinPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinQueryService implements JoinQueryUseCase {

    private final JoinPort joinPort;

    @Override
    public void validateNickName(ValidateValueCommand command) {
        joinPort.checkNickName(command.value());
    }
}
