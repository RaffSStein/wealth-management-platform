package raff.stein.profiler.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Section {

    private Integer id;
    private String sectionCode;
    private String sectionName;
    private List<Feature> features;
}
