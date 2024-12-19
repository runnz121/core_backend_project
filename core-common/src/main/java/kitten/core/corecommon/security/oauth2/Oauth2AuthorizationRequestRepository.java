package kitten.core.corecommon.security.oauth2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kitten.core.corecommon.utils.AuthUtil;
import kitten.core.corecommon.utils.CookieUtils;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Oauth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return CookieUtils.getCookie(request, AuthUtil.OAUTH_REQUEST_COOKIE)
                .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        if (authorizationRequest == null) {
            removeAuthorizationRequestCookies(request, response);
            return;
        }
        CookieUtils.addCookie(response, AuthUtil.OAUTH_REQUEST_COOKIE, CookieUtils.serialize(authorizationRequest), AuthUtil.COOKIE_EXPIRATION);
        String redirectUriLogin = request.getParameter(AuthUtil.REDIRECT_URI);
        if (StringUtils.hasText(redirectUriLogin)) {
            CookieUtils.addCookie(response, AuthUtil.REDIRECT_URI, redirectUriLogin, AuthUtil.COOKIE_EXPIRATION);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request,
                                                                 HttpServletResponse response) {
        return this.loadAuthorizationRequest(request);
    }

    public void removeAuthorizationRequestCookies(HttpServletRequest request,
                                                  HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, AuthUtil.OAUTH_REQUEST_COOKIE);
        CookieUtils.deleteCookie(request, response, AuthUtil.REDIRECT_URI);
    }
}
