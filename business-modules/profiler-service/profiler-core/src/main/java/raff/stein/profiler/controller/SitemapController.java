package raff.stein.profiler.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.SitemapApi;
import org.openapitools.model.SitemapResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import raff.stein.profiler.controller.mapper.SitemapResponseMapper;
import raff.stein.profiler.service.SitemapService;

@RestController
@RequiredArgsConstructor
public class SitemapController implements SitemapApi {

    private final SitemapService sitemapService;
    private static final SitemapResponseMapper sitemapResponseMapper = SitemapResponseMapper.MAPPER;

    @Override
    public ResponseEntity<SitemapResponseDTO> getSitemap(String application) {
        var sections = sitemapService.getSitemapForApplication(application);

        if (sections == null || sections.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        SitemapResponseDTO response = sitemapResponseMapper.toDto(application, sections);
        return ResponseEntity.ok(response);
    }
}
