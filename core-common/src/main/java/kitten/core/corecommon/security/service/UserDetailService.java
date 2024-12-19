package kitten.core.corecommon.security.service;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.corecommon.model.Oauth2UserDetail;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static kitten.core.corecommon.config.exception.CommonError.NOT_FOUND_LOGIN_HISTORY;


@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Users authUserEntity = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CommonRuntimeException(NOT_FOUND_LOGIN_HISTORY));
        return Oauth2UserDetail.ofToken(authUserEntity);
    }
}
