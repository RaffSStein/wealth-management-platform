package raff.stein.profiler.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProfilerCoreWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // specify any interceptors you want to add
        // You can specify path patterns if needed, e.g., .addPathPatterns("/api/**")
    }
}
