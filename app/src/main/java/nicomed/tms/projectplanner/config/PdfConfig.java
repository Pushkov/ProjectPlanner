package nicomed.tms.projectplanner.config;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.pdf.PdfService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.thymeleaf.spring5.SpringTemplateEngine;

@RequiredArgsConstructor
@Configuration
public class PdfConfig {

    private final SpringTemplateEngine springTemplateEngine;

    @Scope("prototype")
    @Bean
    public PdfService getPdfService() {
        return new PdfService(springTemplateEngine);
    }
}
