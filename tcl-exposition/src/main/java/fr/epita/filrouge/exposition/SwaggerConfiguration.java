package fr.epita.filrouge.exposition;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("fr.epita.filrouge.exposition"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder ()//
                .title("Swagger - bibliothèque de films et série") //
                .description("Cette application permet de stocker les films et série et de fournir à un utilisateur" +
                        "une liste personnalisée contenant les films ou série à voir, en cours ou déjà vue.") //
                .license("private use") //
                .licenseUrl("none") //
                .termsOfServiceUrl("") //
                .version("1.0") //
                .contact(new Contact ("Yoss", "", "youssrabou@gmail.com")) //
                .build();
    }
}
