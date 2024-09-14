package kitten.diy.api.application.port.in.query;

import kitten.diy.api.application.port.in.command.command.ValidateValueCommand;

public interface JoinQueryUseCase {

    void validateNickName(ValidateValueCommand command);
}
