package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.command.UploadCommand;

public interface ImagePort {

    void uploadImage(UploadCommand command);
}
