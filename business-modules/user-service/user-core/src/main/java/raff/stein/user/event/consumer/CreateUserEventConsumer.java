package raff.stein.user.event.consumer;

import io.cloudevents.CloudEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import raff.stein.platformcore.messaging.consumer.WMPBaseEventConsumer;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "kafka.topics.user-service.create-user.enabled", havingValue = "true")
public class CreateUserEventConsumer extends WMPBaseEventConsumer {

    @KafkaListener(
            topics = "${kafka.topics.user-service.create-user.name}",
            containerFactory = "kafkaListenerFactory",
            groupId = "${kafka.topics.user-service.create-user.groupId}")
    public void consume(CloudEvent cloudEvent) {
        //TODO: Implement the logic to handle the CreateUser event
    }
}
