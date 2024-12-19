package kitten.core.coredomain.config.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LoggingFilter extends OncePerRequestFilter implements OrderedFilter {

    private static final String X_FORWARDED_FOR_VALUE = "X-Forwarded-For";

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        log.info("<LOGGING:FILTER:INIT: requestURL: {}", request.getRequestURL());
        putRemoteAddr(request);
        filterChain.doFilter(request, response);
        removeRemoteAddr();
    }

    private void putRemoteAddr(final HttpServletRequest request) {
        try {
            final UUID uuid = UUID.randomUUID();
            MDC.put(X_FORWARDED_FOR_VALUE, request.getRemoteAddr());
        } catch (IllegalArgumentException e) {
            log.error("### Error addRemoteAddr");
        }
    }

    private void removeRemoteAddr() {
        try {
            MDC.remove(X_FORWARDED_FOR_VALUE);
        } catch (IllegalArgumentException e) {
            log.error("### Error removeRemoteAddr");
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}