package kitten.core.corecommon.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kitten.core.corecommon.security.service.TokenService;
import kitten.core.corecommon.security.service.UserDetailService;
import kitten.core.corecommon.utils.AuthUtil;
import kitten.core.corecommon.utils.CookieUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Optional;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final TokenService tokenService;
    private final UserDetailService userDetailsService;

    public AuthorizationFilter(AuthenticationManager authenticationManager,
                               UserDetailService userDetailsService,
                               TokenService tokenService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authToken = getAuthToken(request);
        if (StringUtils.hasText(authToken)) {
            Authentication authentication = getAuthentication(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getAuthToken(HttpServletRequest request) {
        Optional<Cookie> cookie = CookieUtils.getCookie(request, AuthUtil.KITTEN_COOKIE_NAME);
        return cookie.map(Cookie::getValue).orElse(null);
    }

    public Authentication getAuthentication(String token){
        String userEmail = tokenService.getUserEmail(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
