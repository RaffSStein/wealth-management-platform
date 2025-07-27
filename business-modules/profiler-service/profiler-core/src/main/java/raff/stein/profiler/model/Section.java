package raff.stein.profiler.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Section {

    private UUID id;
    private String sectionCode;
    private String sectionName;
    private List<Feature> features;
}
