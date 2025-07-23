package raff.stein.platformcore.security.jwt;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import raff.stein.platformcore.security.context.SecurityContextHolder;
import raff.stein.platformcore.security.context.WMPContext;

import java.util.Optional;

/**
 * Interceptor to extract JWT from headers, parse it, and populate the UserContext.
 */
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    private final JwtTokenParser jwtTokenParser;

    public JwtAuthenticationInterceptor(JwtTokenParser jwtTokenParser) {
        this.jwtTokenParser = jwtTokenParser;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        String correlationId = request.getHeader("X-Correlation-ID");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            try {
                Optional<WMPContext> wmpContextOptional = jwtTokenParser.parseTokenAndBuildContext(token, correlationId);
                wmpContextOptional.ifPresent(SecurityContextHolder::setContext);

            } catch (Exception e) {
                throw new RuntimeException("Invalid JWT token", e);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        SecurityContextHolder.clear();
    }
}
