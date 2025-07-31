package raff.stein.platformcore.messaging.configuration.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.broker")
@Data
public class KafkaBrokerConfiguration {

    public static final String DELIMITER = ".";
    private String connectionUri;
    private String serviceAccount;
    private boolean secureConnection;
    private String user;
    private String pwd;
    private String environment;
    private String tenant;
    private String domain;

    private String getEnvironmentTenant() {
        return String.join(DELIMITER, environment, tenant);
    }

    public String prefix(String topicOrStreamId) {
        return String.join(DELIMITER, getEnvironmentTenant(), topicOrStreamId);
    }

}
