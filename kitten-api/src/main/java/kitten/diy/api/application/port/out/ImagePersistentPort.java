package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.command.command.UploadCommand;

public interface ImagePersistentPort {

    void uploadImage(UploadCommand command);
}
