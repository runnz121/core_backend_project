package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.command.command.ValidateValueCommand;
import kitten.diy.api.application.port.in.query.JoinQueryUseCase;
import kitten.diy.api.application.port.in.query.data.CheckNickNameData;
import kitten.diy.api.application.port.out.JoinFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinQueryService implements JoinQueryUseCase {

    private final JoinFetchPort joinFetchPort;

    @Override
    public CheckNickNameData checkNickName(ValidateValueCommand command) {
        Boolean isExists = joinFetchPort.checkNickName(command.value());
        return CheckNickNameData.of(isExists);
    }
}
