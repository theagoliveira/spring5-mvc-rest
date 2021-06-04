package guru.springframework.spring5mvcrest.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30).select()
                                                   .apis(RequestHandlerSelectors.any())
                                                   .paths(PathSelectors.any())
                                                   .build()
                                                   .pathMapping("/")
                                                   .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        var contact = new Contact(
            "Thiago Cavalcante", "https://github.com/theagoliveira", "thiago@example.com"
        );

        return new ApiInfo(
            "Spring Framework Guru", "Spring Framework 5: Beginner to Guru", "1.0",
            "Terms of Service: TODO", contact, "Apache License Version 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>()
        );
    }

}
