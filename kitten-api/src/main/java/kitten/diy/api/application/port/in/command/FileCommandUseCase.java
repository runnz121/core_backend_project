package kitten.diy.api.application.port.in.command;

import kitten.diy.api.application.port.in.command.command.UploadCommand;

public interface FileCommandUseCase {

    void upload(UploadCommand command);
}
