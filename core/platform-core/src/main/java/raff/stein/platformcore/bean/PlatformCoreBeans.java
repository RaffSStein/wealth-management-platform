package raff.stein.platformcore.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import raff.stein.platformcore.security.jwt.JwtAuthenticationInterceptor;
import raff.stein.platformcore.security.jwt.JwtTokenParser;

@Configuration
public class PlatformCoreBeans {

    @Bean
    public JwtAuthenticationInterceptor jwtAuthenticationInterceptor(JwtTokenParser parser) {
        return new JwtAuthenticationInterceptor(parser);
    }
}
