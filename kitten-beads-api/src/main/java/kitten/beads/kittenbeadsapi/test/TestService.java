package kitten.beads.kittenbeadsapi.test;

import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TestService {

    private final UsersRepository usersRepository;

    public void test() {
        Users users = new Users();
        usersRepository.existsByEmail("hi");
    }
}
