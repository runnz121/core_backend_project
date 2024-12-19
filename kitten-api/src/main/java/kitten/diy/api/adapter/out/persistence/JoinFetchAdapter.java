package kitten.diy.api.adapter.out.persistence;

import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.application.port.out.JoinFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class JoinFetchAdapter implements JoinFetchPort {

    private final UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public Boolean checkNickName(String nickName) {
        return usersRepository.existsByNickName(nickName);
    }
}
