package raff.stein.platformcore.security.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import raff.stein.platformcore.security.context.SecurityContextFilter;

import java.security.PublicKey;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfiguration {

    @Bean
    public JwtTokenParser jwtTokenParser(JwtProperties properties) {
        PublicKey publicKey = JwtPublicKeyProvider.loadPublicKey(properties.getPublicKeyPath());
        return new JwtTokenParser(publicKey);
    }

    @Bean
    public FilterRegistrationBean<SecurityContextFilter> securityContextFilter(JwtTokenParser parser, JwtProperties properties) {
        FilterRegistrationBean<SecurityContextFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new SecurityContextFilter(parser, properties));
        registration.setOrder(1);
        registration.addUrlPatterns("/*"); // Apply to all endpoints
        return registration;
    }
}
