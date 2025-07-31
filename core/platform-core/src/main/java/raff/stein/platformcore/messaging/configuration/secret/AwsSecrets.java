package raff.stein.platformcore.messaging.configuration.secret;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.crypto.SealedObject;

@SuperBuilder
@Data
public abstract class AwsSecrets {

    private String user;
    private SealedObject password;
}
