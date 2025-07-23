package raff.stein.platformcore.messaging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventEnvelope<T> {

    private String eventId;
    private String correlationId;
    private String eventType;
    private T payload;
    private Instant timestamp;
}
