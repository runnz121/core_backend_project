package kitten.beads.kittenbeadsapi.users.service;

import kitten.beads.kittenbeadsapi.users.dto.UserInfoData;
import kitten.core.corecommon.config.exception.CommonError;
import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public UserInfoData getUserInfo(String userEmail) {
        Users foundedUsers = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CommonRuntimeException(CommonError.NOT_FOUND_LOGIN_HISTORY));
        return UserInfoData.of(foundedUsers.getEmail(), 0);
    }
}
