package kitten.diy.api.adapter.out.persistence;

import kitten.core.coredomain.model.AuthRoles;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.application.port.in.command.command.JoinCommand;
import kitten.diy.api.application.port.out.JoinPersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class JoinCommandAdapter implements JoinPersistentPort {

    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public void saveNewUser(JoinCommand command) {
        Users newUser = Users.builder()
                .email(command.email())
                .authRoles(AuthRoles.USER)
                .nickName(command.nickName())
                .phoneNumber(command.phoneNumber())
                .profileImgUrl(command.profileImageUrl())
                .build();
        usersRepository.save(newUser);
    }
}
