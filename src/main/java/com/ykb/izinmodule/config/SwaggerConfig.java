package com.ykb.izinmodule.config;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(globalParameterList())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.ykb.izinmodule.api"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("IZIN MODULE")
                .description("IZIN MODULE rest services")
                .version("1.0.0")
                .build();
    }

    private List<Parameter> globalParameterList() {
        val languageHeader =
                new ParameterBuilder()
                        .name("Accept-Language")
                        .modelRef(new ModelRef("string"))
                        .required(true)
                        .parameterType("header")
                        .description("pt-TR or pt-EN")
                        .build();

        return Collections.singletonList(languageHeader);
    }
}
