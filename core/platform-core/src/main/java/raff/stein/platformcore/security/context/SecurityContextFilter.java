package raff.stein.platformcore.security.context;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import raff.stein.platformcore.security.jwt.JwtProperties;
import raff.stein.platformcore.security.jwt.JwtTokenParser;

import java.io.IOException;
import java.util.Optional;

/**
 * Filter that extracts the JWT from the request, parses it,
 * and stores the authenticated user in the context.
 */
public class SecurityContextFilter implements Filter {

    private final JwtTokenParser tokenParser;
    private final JwtProperties jwtProperties;

    public SecurityContextFilter(JwtTokenParser tokenParser, JwtProperties jwtProperties) {
        this.tokenParser = tokenParser;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String authHeader = httpRequest.getHeader(jwtProperties.getHeader());
            String correlationId = httpRequest.getHeader("X-Correlation-ID");

            if (isTokenPresent(authHeader)) {
                String token = extractToken(authHeader);
                Optional<WMPContext> wmpContextOptional = tokenParser.parseTokenAndBuildContext(token, correlationId);
                wmpContextOptional.ifPresent(SecurityContextHolder::setContext);
            }

            chain.doFilter(request, response);

        } finally {
            SecurityContextHolder.clear();
        }
    }

    /**
     * Checks if the Authorization header contains a Bearer token.
     */
    private boolean isTokenPresent(String authHeader) {
        return StringUtils.hasText(authHeader) && authHeader.startsWith(jwtProperties.getPrefix() + " ");
    }

    /**
     * Extracts the JWT from the Authorization header.
     */
    private String extractToken(String authHeader) {
        return authHeader.substring((jwtProperties.getPrefix() + " ").length());
    }
}
