package raff.stein.platformcore.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import raff.stein.platformcore.security.context.WMPContext;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Responsible for parsing and validating the JWT token.
 */
@Slf4j
public class JwtTokenParser {

    private final PublicKey publicKey;

    public JwtTokenParser(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * Parses and validates a JWT using the provided public key.
     * @param token the JWT as a String
     * @return the parsed claims
     * @throws JwtException if the token is invalid or expired
     */
    public Optional<WMPContext> parseTokenAndBuildContext(String token, String correlationId) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(token);
            Claims claims = claimsJws.getPayload();

            WMPContext context = WMPContext.builder()
                    .userId(claims.get("userId", String.class))
                    .email(claims.get("email", String.class))
                    .roles(Set.copyOf(claims.get("roles", List.class)))
                    //TODO: probably not needed, as we can get it from the user-service
                    .bankCode(claims.get("bankCode", String.class))
                    .correlationId(correlationId)
                    .build();
            return Optional.of(context);
        } catch (JwtException e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }
}
