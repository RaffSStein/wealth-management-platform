package raff.stein.platformcore.correlation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import raff.stein.platformcore.correlation.interceptor.HttpCorrelationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final HttpCorrelationInterceptor interceptor;

    @Autowired
    public WebConfig(HttpCorrelationInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}

