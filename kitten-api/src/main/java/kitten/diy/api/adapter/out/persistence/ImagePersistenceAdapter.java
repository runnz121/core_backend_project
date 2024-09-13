package kitten.diy.api.adapter.out.persistence;

import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.application.port.in.command.UploadCommand;
import kitten.diy.api.application.port.out.ImagePersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImagePersistenceAdapter implements ImagePersistentPort {

    private final UsersRepository usersRepository;

    @Override
    public void uploadImage(UploadCommand command) {

    }
}
