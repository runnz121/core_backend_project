package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.command.command.JoinCommand;

public interface JoinPersistentPort {

    void saveNewUser(JoinCommand command);
}
