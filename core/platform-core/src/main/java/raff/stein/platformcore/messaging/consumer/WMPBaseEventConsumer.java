package raff.stein.platformcore.messaging.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class WMPBaseEventConsumer {

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    protected <T> Optional<T> getEventPayload(CloudEvent cloudEvent, Class<T> clazz) {
        log.info("CloudEvent received for class: [{}], eventId; [{}]",
                clazz.getName(),
                cloudEvent.getId());
        log.debug("CloudEvent received for class: [{}], eventId; [{}], data: [{}]",
                clazz.getName(),
                cloudEvent.getId(),
                cloudEvent.getData());
        CloudEventData cloudEventData = cloudEvent.getData();
        if(cloudEventData != null ) {
            try {
                return Optional.ofNullable(objectMapper.readValue(cloudEventData.toBytes(), clazz));
            } catch (IOException e) {
                log.error("Failed to parse event payload: {}", e.getMessage());
            }
        }
        return Optional.empty();
    }

}
