package raff.stein.platformcore.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    /**
     * Location of the public key file (e.g., classpath:keys/public_key.pem)
     */
    private Resource publicKeyPath;

    private String header = "Authorization";
    private String prefix = "Bearer ";

    public Resource getPublicKeyPath() {
        return publicKeyPath;
    }

    public void setPublicKeyPath(Resource publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
