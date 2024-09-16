package kitten.diy.api.application.domain.service;


import kitten.diy.api.application.port.in.command.FileCommandUseCase;
import kitten.diy.api.application.port.in.command.command.UploadCommand;
import kitten.diy.api.application.port.out.ImageInfraPort;
import kitten.diy.api.application.port.out.ImagePersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileCommandService implements FileCommandUseCase {

    private final ImagePersistentPort imagePersistentPort;
    private final ImageInfraPort imageInfraPort;

    @Override
    @Transactional
    public String upload(UploadCommand command) {
        imagePersistentPort.uploadImage(command);
        return imageInfraPort.uploadImage(command);
    }
}
