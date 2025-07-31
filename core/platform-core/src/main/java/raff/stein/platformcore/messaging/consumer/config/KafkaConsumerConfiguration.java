package raff.stein.platformcore.messaging.consumer.config;

import io.cloudevents.kafka.CloudEventDeserializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import raff.stein.platformcore.messaging.configuration.kafka.KafkaConfiguration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Profile("!integration-test")
public class KafkaConsumerConfiguration {

    private static final String EARLIEST_OFFSET = "earliest";
    private final KafkaConfiguration kafkaConfiguration;

    @Bean(name = "kafkaListenerFactory")
    @Primary
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRecordFilterStrategy(new SecurityContextInitializingStrategy());
        return factory;
    }

    @Bean(name = "consumerFactory")
    @Primary
    public ConsumerFactory<Object, Object> consumerFactory() {
        Map<String, Object> props = kafkaConfiguration.getBasicBrokerProperties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, CloudEventDeserializer.class);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 45000);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, EARLIEST_OFFSET);
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
