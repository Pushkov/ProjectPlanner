package nicomed.tms.projectplanner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("dev")
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins(
                                "http://localhost:8080",
                                "http://localhost:8082",
                                "http://localhost:8086",
                                "http://172.17.13.31"
                        );
            }
        };
    }

    /*
        public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:8082",
                        "http://172.17.13.31:8080",
                        "http://172.17.13.31:8082",
                        "http://localhost:8080",
                        "http://172.17.13.30")
                .allowedMethods("*")
        ;
    }
     */

}
