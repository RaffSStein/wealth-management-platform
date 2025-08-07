package raff.stein.platformcore.messaging.consumer;

import io.cloudevents.CloudEvent;

import java.util.Optional;

public interface EventConsumer {

    <T> Optional<T> getEventPayload(CloudEvent cloudEvent, Class<T> clazz);
}
