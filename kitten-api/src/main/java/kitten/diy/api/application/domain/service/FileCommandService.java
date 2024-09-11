package kitten.diy.api.application.domain.service;


import kitten.diy.api.application.port.in.FileCommandUseCase;
import kitten.diy.api.application.port.in.command.UploadCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileCommandService implements FileCommandUseCase {

    @Override
    @Transactional
    public void upload(UploadCommand command) {

    }
}
