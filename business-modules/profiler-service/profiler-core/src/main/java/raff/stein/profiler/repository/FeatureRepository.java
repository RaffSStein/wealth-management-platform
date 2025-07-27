package raff.stein.profiler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raff.stein.profiler.model.entity.FeatureEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeatureRepository extends JpaRepository<FeatureEntity, UUID> {

    List<FeatureEntity> findAllBySectionId(UUID sectionId);

    List<FeatureEntity> findAllBySectionIdIn(List<UUID> sectionIds);
}

