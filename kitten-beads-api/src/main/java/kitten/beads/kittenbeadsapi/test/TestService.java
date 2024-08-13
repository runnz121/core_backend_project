package kitten.beads.kittenbeadsapi.test;

import kitten.core.coredomain.model.AuthRoles;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final UsersRepository usersRepository;

    public void test() {
        Users users = Users.builder()
                .authRoles(AuthRoles.USER)
                .email("runnz121@gmail.com")
                .build();

        Users save = usersRepository.save(users);

        System.out.println(save);
    }
}
