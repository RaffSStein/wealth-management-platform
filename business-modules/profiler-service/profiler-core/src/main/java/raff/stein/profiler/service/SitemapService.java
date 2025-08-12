package raff.stein.profiler.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raff.stein.profiler.model.Feature;
import raff.stein.profiler.model.Section;
import raff.stein.profiler.model.entity.FeatureEntity;
import raff.stein.profiler.model.entity.SectionEntity;
import raff.stein.profiler.model.entity.mapper.FeatureEntityToFeatureMapper;
import raff.stein.profiler.model.entity.mapper.SectionEntityToSectionMapper;
import raff.stein.profiler.repository.FeatureRepository;
import raff.stein.profiler.repository.SectionRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SitemapService {

    private final SectionRepository sectionRepository;
    private final FeatureRepository featureRepository;
    private static final SectionEntityToSectionMapper sectionMapper = SectionEntityToSectionMapper.MAPPER;
    private static final FeatureEntityToFeatureMapper featureMapper = FeatureEntityToFeatureMapper.MAPPER;


    @Transactional(readOnly = true)
    public List<Section> getSitemapForApplication(String application) {
        // Validate the application identifier
        if (StringUtils.isBlank(application) || application.length() > 50) {
            throw new IllegalArgumentException("Invalid application identifier");
        }
        // Normalize the application identifier
        application = application.toUpperCase();
        // Fetch sections for the given application
        List<SectionEntity> sectionEntities = sectionRepository.findAllByApplication(application);
        if (sectionEntities.isEmpty()) {
            return Collections.emptyList();
        }
        List<Integer> sectionIds = sectionEntities.stream()
                .map(SectionEntity::getId)
                .toList();

        List<FeatureEntity> allFeatureEntities = featureRepository.findAllBySectionIdIn(sectionIds);

        Map<Integer, List<FeatureEntity>> featuresBySectionId = allFeatureEntities.stream()
                .filter(fe -> fe.getSection() != null && fe.getSection().getId() != null)
                .collect(Collectors.groupingBy(fe -> fe.getSection().getId()));

        List<Section> sections = new ArrayList<>();
        for (SectionEntity sectionEntity : sectionEntities) {
            List<FeatureEntity> featureEntities = featuresBySectionId.getOrDefault(sectionEntity.getId(), Collections.emptyList());

            Section section = sectionMapper.toSection(sectionEntity);
            List<Feature> features = featureEntities.stream()
                    .map(featureMapper::toFeature)
                    .toList();
            section.setFeatures(features);
            sections.add(section);
        }
        return sections;
    }
}
