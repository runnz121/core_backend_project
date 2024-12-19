package kitten.diy.api.adapter.out.client;

import kitten.core.corecommon.security.service.TokenService;
import kitten.core.corecommon.utils.AuthUtil;
import kitten.core.corecommon.utils.CookieUtils;
import kitten.core.coredomain.model.AuthRoles;
import kitten.diy.api.application.port.in.command.command.JoinCommand;
import kitten.diy.api.application.port.out.JoinTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JoinTokenAdapter implements JoinTokenPort {

    private static final String AUTHORIZATION = "Authorization";

    private final TokenService tokenService;

    @Override
    public String createToken(JoinCommand command) {
        String accessToken = tokenService.generateJwt(AuthRoles.ROLE_USER, command.email());
        CookieUtils.deleteCookie(command.request(), command.response(), AuthUtil.KITTEN_COOKIE_NAME);
        CookieUtils.addCookie(command.response(), AuthUtil.KITTEN_COOKIE_NAME, accessToken, AuthUtil.MAX_AGE);
        command.response().setHeader(AUTHORIZATION, accessToken);
        return accessToken;
    }
}
