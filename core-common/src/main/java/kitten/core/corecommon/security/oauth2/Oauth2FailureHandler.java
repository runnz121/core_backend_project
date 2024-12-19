package kitten.core.corecommon.security.oauth2;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kitten.core.corecommon.utils.AuthUtil;
import kitten.core.corecommon.utils.CookieUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Oauth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final Oauth2AuthorizationRequestRepository repository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        String targetUrl = getTargetUrl(request, exception);
        repository.removeAuthorizationRequestCookies(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String getTargetUrl(HttpServletRequest request,
                                AuthenticationException exception) {
        String targetUrl = CookieUtils.getCookie(request, AuthUtil.REDIRECT_URI)
                .map(Cookie::getValue)
                .orElse((AuthUtil.DEFAULT_PATH));

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam(AuthUtil.ERROR, exception.getLocalizedMessage())
                .build().toUriString();
    }
}
