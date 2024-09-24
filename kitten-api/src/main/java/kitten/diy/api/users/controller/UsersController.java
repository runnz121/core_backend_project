package kitten.diy.api.users.controller;

import kitten.diy.api.users.dto.UserInfoData;
import kitten.diy.api.users.service.UsersService;
import kitten.core.corecommon.security.jwt.AccessAccount;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Secured(value = "ROLE_USER")
    @GetMapping("/auth/me")
    public UserInfoData getUserMe(@AccessAccount CurrentAccount account) {
        return usersService.getUserInfo(account.getUserEmail());
    }
}
