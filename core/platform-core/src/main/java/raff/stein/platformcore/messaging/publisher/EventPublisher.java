package raff.stein.platformcore.messaging.publisher;

import lombok.NonNull;
import raff.stein.platformcore.messaging.publisher.model.EventData;

public interface EventPublisher {

    void publishCloudEvent(@NonNull String topic, @NonNull EventData eventData);
}
