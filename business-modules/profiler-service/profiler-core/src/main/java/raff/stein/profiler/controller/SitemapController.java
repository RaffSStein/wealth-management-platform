package raff.stein.profiler.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.SitemapApi;
import org.openapitools.model.SitemapResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import raff.stein.profiler.controller.mapper.SitemapResponseDTOMapper;
import raff.stein.profiler.service.SitemapService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SitemapController implements SitemapApi {

    private final SitemapService sitemapService;
    private static final SitemapResponseDTOMapper sitemapResponseMapper = SitemapResponseDTOMapper.MAPPER;

    @Override
    public ResponseEntity<SitemapResponseDTO> getSitemap(String application) {
        var sections = sitemapService.getSitemapForApplication(application);

        if (sections == null || sections.isEmpty()) {
            log.info("No sections found for application: {}", application);
            return ResponseEntity.notFound().build();
        }
        SitemapResponseDTO response = sitemapResponseMapper.toDto(application, sections);
        return ResponseEntity.ok(response);
    }
}
