package raff.stein.user.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import raff.stein.platformcore.security.jwt.JwtAuthenticationInterceptor;

@Configuration
public class UserCoreWebConfig implements WebMvcConfigurer {

    private final JwtAuthenticationInterceptor jwtAuthenticationInterceptor;

    public UserCoreWebConfig(JwtAuthenticationInterceptor interceptor) {
        this.jwtAuthenticationInterceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthenticationInterceptor);
        // You can specify path patterns if needed, e.g., .addPathPatterns("/api/**")
    }
}
