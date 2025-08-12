package raff.stein.document.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class File {

    private UUID customerId;
    private String fileName;
    private Long fileSize;
    private String mimeType;
    private String description;
    private String documentType;
    private Set<Metadata> metadata;

    private MultipartFile multipartFile;
}
