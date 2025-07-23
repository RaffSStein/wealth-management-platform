package raff.stein.platformcore.messaging.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import raff.stein.platformcore.messaging.model.EventEnvelope;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public <T> void publish(String topic, T payload, String eventType, String correlationId) {

        if (topic == null || topic.isEmpty()) {
            throw new IllegalArgumentException("Topic must not be null or empty");
        }

        if (payload == null) {
            throw new IllegalArgumentException("Payload must not be null");
        }

        if (eventType == null || eventType.isEmpty()) {
            throw new IllegalArgumentException("Event type must not be null or empty");
        }

        EventEnvelope<T> envelope = EventEnvelope.<T>builder()
                .eventId(UUID.randomUUID().toString())
                .eventType(eventType)
                .payload(payload)
                .correlationId(correlationId)
                .timestamp(Instant.now())
                .build();

        kafkaTemplate.send(topic, envelope.getEventId(), envelope);
    }
}
