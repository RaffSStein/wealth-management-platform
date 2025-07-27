package raff.stein.profiler.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Entity representing a Feature in the application.
 * Mirrors the structure of FeatureDTO for MapStruct mapping.
 */
@Data
@Builder
public class Feature {

    private UUID id;
    private String featureCode;
    private String featureName;

}
