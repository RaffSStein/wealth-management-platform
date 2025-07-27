package raff.stein.profiler.controller;

import org.openapitools.api.SitemapApi;
import org.openapitools.model.SitemapResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SitemapController implements SitemapApi {

    @Override
    public ResponseEntity<SitemapResponse> getSitemap(String application) {
        return null;
    }
}
