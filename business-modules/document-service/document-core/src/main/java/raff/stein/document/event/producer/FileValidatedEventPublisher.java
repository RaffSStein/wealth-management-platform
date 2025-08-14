package raff.stein.document.event.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import raff.stein.document.event.producer.mapper.FileToFileValidatedMapper;
import raff.stein.document.model.File;
import raff.stein.document.service.validation.FileValidationResult;
import raff.stein.platformcore.messaging.publisher.WMPBaseEventPublisher;
import raff.stein.platformcore.messaging.publisher.model.EventData;

@Slf4j
@Component
@ConditionalOnProperty(name = "kafka.topics.document-service.file-validated.enabled", havingValue = "true")
public class FileValidatedEventPublisher {

    private final WMPBaseEventPublisher wmpBaseEventPublisher;
    private final String fileValidatedTopic;
    private static final FileToFileValidatedMapper fileToFileValidatedMapper = FileToFileValidatedMapper.MAPPER;

    public FileValidatedEventPublisher(
            WMPBaseEventPublisher wmpBaseEventPublisher,
            @Value("${kafka.topics.document-service.file-validated.name}") String fileValidatedTopic) {
        this.wmpBaseEventPublisher = wmpBaseEventPublisher;
        this.fileValidatedTopic = fileValidatedTopic;
    }

    public void publishDocumentValidatedEvent(File file, FileValidationResult fileValidationResult) {
        var fileValidatedEvent = fileToFileValidatedMapper.toFileValidatedEvent(file, fileValidationResult);
        var eventData = new EventData(fileValidatedEvent);
        wmpBaseEventPublisher.publishCloudEvent(fileValidatedTopic, eventData);
        log.info("Published FileValidatedEvent for file with ID: [{}]", file.getId());
    }


}
