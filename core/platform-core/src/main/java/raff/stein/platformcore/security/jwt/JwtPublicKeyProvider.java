package raff.stein.platformcore.security.jwt;

import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Utility to load a public key from a PEM file.
 */
public class JwtPublicKeyProvider {

    private JwtPublicKeyProvider() {
        // Prevent instantiation
    }

    public static PublicKey loadPublicKey(Resource pemResource) {
        try (InputStream is = pemResource.getInputStream()) {
            String key = StreamUtils.copyToString(is, StandardCharsets.UTF_8)
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(key);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            return KeyFactory.getInstance("RSA").generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load public key", e);
        }
    }
}
