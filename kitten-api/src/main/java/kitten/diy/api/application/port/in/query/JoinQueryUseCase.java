package kitten.diy.api.application.port.in.query;

import kitten.diy.api.application.port.in.command.command.ValidateValueCommand;
import kitten.diy.api.application.port.in.query.data.CheckNickNameData;

public interface JoinQueryUseCase {

    CheckNickNameData checkNickName(ValidateValueCommand command);
}
