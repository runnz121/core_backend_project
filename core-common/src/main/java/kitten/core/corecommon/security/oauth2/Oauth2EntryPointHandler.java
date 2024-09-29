package kitten.core.corecommon.security.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j
public class Oauth2EntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        log.error("<AUTHENTICATION:인증이 없음, request : {}, response : {}", request.getRequestURL(), response.getHeaderNames());

        response
                .sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getLocalizedMessage());
    }
}
