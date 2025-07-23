package raff.stein.platformcore.messaging.publisher.correlation;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static raff.stein.platformcore.correlation.utils.CorrelationUtils.CORRELATION_ID_HEADER;
import static raff.stein.platformcore.correlation.utils.CorrelationUtils.getCorrelationId;

@Component
public class CorrelationInterceptor implements ProducerInterceptor<String, Object> {


    @Override
    public ProducerRecord<String, Object> onSend(ProducerRecord<String, Object> producerRecord) {
        // Retrieve the correlation ID from MDC (Mapped Diagnostic Context)
        String correlationId = MDC.get(getCorrelationId());
        if (correlationId != null) {
            producerRecord.headers().add(CORRELATION_ID_HEADER, correlationId.getBytes(StandardCharsets.UTF_8));
        }
        return producerRecord;
    }


    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        // No-op
    }

    @Override
    public void close() {
        // No-op
    }

    @Override
    public void configure(Map<String, ?> configs) {
        // No-op
    }
}

