package raff.stein.platformcore.messaging.configuration.kafka;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import raff.stein.platformcore.messaging.configuration.secret.AwsSecrets;

@SuperBuilder
@Getter
public class KafkaAwsSecrets extends AwsSecrets {

    private final String server;
    private final boolean secure;
}
