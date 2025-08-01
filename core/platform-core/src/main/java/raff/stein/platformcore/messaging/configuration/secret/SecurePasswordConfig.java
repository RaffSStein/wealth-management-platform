package raff.stein.platformcore.messaging.configuration.secret;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
@ConfigurationProperties(prefix = "secure.password")
// This class is used to configure secure password properties.
public class SecurePasswordConfig {

    private static final String AES = "AES";

    @Getter
    private String algorithm = AES;

    @Setter
    private String keyAlgorithm = AES;

    private SecretKey aesKey;
    private Cipher passwordCipher;

    @PostConstruct
    public SecurePasswordConfig initialize() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        passwordCipher = Cipher.getInstance(algorithm);
        KeyGenerator keyGenerator = KeyGenerator.getInstance(keyAlgorithm);
        aesKey = keyGenerator.generateKey();
        passwordCipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return this;
    }

    public SealedObject getSecurePassword(String password) throws IllegalBlockSizeException, IOException {
        return new SealedObject(password, passwordCipher);
    }

    public String getPlainValue(SealedObject securePassword)
            throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeyException {
        return (String) securePassword.getObject(aesKey);
    }
}
