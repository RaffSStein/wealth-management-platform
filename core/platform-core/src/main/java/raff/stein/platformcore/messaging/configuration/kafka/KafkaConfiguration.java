package raff.stein.platformcore.messaging.configuration.kafka;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import raff.stein.platformcore.messaging.configuration.secret.SecretManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaConfiguration {

    // constants
    private static final String SASL_JAAS_CONFIG = "sasl.jaas.config";
    private static final String SASL_JAAS_CONFIG_VALUE_FORMAT_EBS =
            "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";";
    private static final String SASL_MECHANISM = "sasl.mechanism";
    private static final String SSL_PROTOCOL = "ssl.protocol";
    private static final String SSL_ENABLED_PROTOCOLS = "ssl.enabled.protocols";
    private static final String SSL_ENDPOINT_IDENTIFICATION_ALGORITHM = "ssl.endpoint.identification.algorithm";

    @NonNull
    private SecretManager secretManager;

    public Map<String, Object> getBasicBrokerProperties() {
        Map<String, Object> brokerProperties = new HashMap<>();
        Optional<KafkaAwsSecrets> kafkaAwsSecrets = secretManager.getKafkaAwsSecrets();
        if (kafkaAwsSecrets.isPresent()) {
            KafkaAwsSecrets secrets = kafkaAwsSecrets.get();
            brokerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, secrets.getServer());

            if(secrets.isSecure()) {
                brokerProperties.putAll(getCommonSecurityProperties());
                brokerProperties.put(
                        SASL_JAAS_CONFIG,
                        secretManager.applyBrokerSecrets(SASL_JAAS_CONFIG_VALUE_FORMAT_EBS));
            }
        }
        return brokerProperties;

    }

    private Map<String,Object> getCommonSecurityProperties() {
        Map<String, Object> securityProperties = new HashMap<>();
        securityProperties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        securityProperties.put(SSL_ENDPOINT_IDENTIFICATION_ALGORITHM, "https");
        securityProperties.put(SSL_PROTOCOL, "TLS");
        securityProperties.put(SSL_ENABLED_PROTOCOLS, "TLSv1.2");
        securityProperties.put(SASL_MECHANISM, "PLAIN");
        return securityProperties;
    }

}

