package kitten.diy.api.application.port.out;

import kitten.core.coredomain.user.entity.Users;

import java.util.Optional;

public interface UserFetchPort {

    Optional<Users> getOptionalUsers(String userEmail);
}
