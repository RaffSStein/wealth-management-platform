package raff.stein.platformcore.security.jwt;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import raff.stein.platformcore.security.context.SecurityContextHolder;
import raff.stein.platformcore.security.context.WMPContext;

import java.util.Optional;
import java.util.UUID;

/**
 * Interceptor to extract JWT from headers, parse it, and populate the UserContext.
 */
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    //TODO: move correlation interceptor to another class
    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

    private final JwtTokenParser jwtTokenParser;

    public JwtAuthenticationInterceptor(JwtTokenParser jwtTokenParser) {
        this.jwtTokenParser = jwtTokenParser;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        String correlationId = request.getHeader(CORRELATION_ID_HEADER);

        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            try {
                Optional<WMPContext> wmpContextOptional = jwtTokenParser.parseTokenAndBuildContext(token, correlationId);
                wmpContextOptional.ifPresent( context -> {
                    // Set the context in SecurityContextHolder
                    SecurityContextHolder.setContext(context);
                    // Set MDC for logging
                    MDC.put("userId", context.getUserId());
                    MDC.put("email", context.getEmail());
                    MDC.put("company", context.getCompany());
                    MDC.put("correlationId", context.getCorrelationId());
                });

            } catch (Exception e) {
                throw new RuntimeException("Invalid JWT token", e);
            }
        }
        else {
            throw new RuntimeException("No JWT token provided in request");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        SecurityContextHolder.clear();
        MDC.clear();
    }
}
