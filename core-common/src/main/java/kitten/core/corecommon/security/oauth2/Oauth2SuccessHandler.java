package kitten.core.corecommon.security.oauth2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kitten.core.corecommon.model.Oauth2UserDetail;
import kitten.core.corecommon.security.service.TokenService;
import kitten.core.corecommon.utils.AuthUtil;
import kitten.core.corecommon.utils.CookieUtils;
import kitten.core.coredomain.model.AuthRoles;
import kitten.core.coredomain.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final UsersRepository usersRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        Oauth2UserDetail userPrincipal = (Oauth2UserDetail) authentication.getPrincipal();
        String userEmail = userPrincipal.email();
        String userRole = getUserRole(userPrincipal);
        String accessToken = tokenService.generateJwt(userRole, userEmail);
        CookieUtils.deleteCookie(request, response, AuthUtil.KITTEN_COOKIE_NAME);
        CookieUtils.addCookie(response, AuthUtil.KITTEN_COOKIE_NAME, accessToken, AuthUtil.MAX_AGE);
        sendRedirectUrl(request, response, accessToken, userEmail);
    }

    private void sendRedirectUrl(HttpServletRequest request,
                                 HttpServletResponse response,
                                 String accessToken,
                                 String userEmail) throws IOException {
        String targetUrl = UriComponentsBuilder
                .fromUriString(getRedirectUrl(request.getRequestURL().toString()))
                .queryParam(AuthUtil.USER_EMAIL, userEmail)
                .queryParam("accessToken", accessToken)
                .build()
                .toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String getUserRole(Oauth2UserDetail userPrincipal) {
        if (isExistsUser(userPrincipal.email())) {
            return AuthRoles.ROLE_USER;
        }
        return AuthRoles.ROLE_ANONYMOUS;
    }

    private boolean isExistsUser(String email) {
        return usersRepository.existsByEmail(email);
    }

    private String getRedirectUrl(String requestURL) {
        if (AuthUtil.PUBLIC_CALL_BACK.equals(requestURL)) {
            return AuthUtil.PROD_REDIRECT_URL;
        }
        return AuthUtil.REDIRECT_URL;
    }
}

