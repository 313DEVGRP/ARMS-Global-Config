package com.arms.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .consumes(getConsumeContentTypes())
            .produces(getProduceContentTypes())
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.arms"))
            .paths(PathSelectors.any())
            .build()
            .genericModelSubstitutes(Mono.class, Flux.class)
            ;
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumeContentTypes = new HashSet<>();

        consumeContentTypes.add("application/json;charset=UTF-8");
        consumeContentTypes.add("application/x-www-form-urlencoded");

        return consumeContentTypes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produceContentTypes = new HashSet<>();

        produceContentTypes.add("application/json;charset=UTF-8");

        return produceContentTypes;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("ARMS API")
            .description("ARMS API")
            .build();
    }

}
