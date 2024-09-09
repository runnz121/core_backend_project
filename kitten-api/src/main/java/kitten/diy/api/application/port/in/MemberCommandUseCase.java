package kitten.diy.api.application.port.in;

import kitten.diy.api.application.port.in.command.JoinCommand;

public interface MemberCommandUseCase {

    void agreeTerms(JoinCommand command);
}
