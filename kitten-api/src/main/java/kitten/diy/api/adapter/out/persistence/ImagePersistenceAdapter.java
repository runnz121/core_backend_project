package kitten.diy.api.adapter.out.persistence;

import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.application.port.in.command.UploadCommand;
import kitten.diy.api.application.port.out.ImagePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImagePersistenceAdapter implements ImagePort {

    private final UsersRepository usersRepository;

    @Override
    public void uploadImage(UploadCommand command) {

    }
}
