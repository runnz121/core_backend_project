package kitten.diy.api.adapter.out.persistence;

import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.application.port.out.UserFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFetchAdapter implements UserFetchPort {

    private final UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Users> getOptionalUsers(String userEmail) {
        return usersRepository.findByEmail(userEmail);
    }
}
