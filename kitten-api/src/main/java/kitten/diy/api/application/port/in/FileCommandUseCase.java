package kitten.diy.api.application.port.in;

import kitten.diy.api.application.port.in.command.UploadCommand;

public interface FileCommandUseCase {

    void upload(UploadCommand command);
}
