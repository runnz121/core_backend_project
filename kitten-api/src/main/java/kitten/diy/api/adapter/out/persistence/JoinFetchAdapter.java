package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.adapter.out.consts.UserErrorCode;
import kitten.diy.api.application.port.out.JoinPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class JoinFetchAdapter implements JoinPort {

    private final UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public void checkNickName(String nickName) {
        if (usersRepository.existsByNickName(nickName)) {
            throw new CommonRuntimeException(UserErrorCode.NICK_NAME_ALREADY_EXISTS);
        };
    }
}
