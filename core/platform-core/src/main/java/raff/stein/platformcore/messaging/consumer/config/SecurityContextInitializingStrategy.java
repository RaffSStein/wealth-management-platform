package raff.stein.platformcore.messaging.consumer.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

public class SecurityContextInitializingStrategy implements RecordFilterStrategy<Object, Object> {

    @Override
    public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
        // Initialize security context here if needed
        // For example, you can set the security context based on the record
        // This is a placeholder implementation
        return false; // Return true to filter out the record, false to keep it
    }
}
