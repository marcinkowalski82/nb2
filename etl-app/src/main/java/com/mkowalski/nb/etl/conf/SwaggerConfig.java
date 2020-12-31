/*
 *  This file contains information that is the confidential, proprietary and trade secret information of IGT or its affiliates and may be exempt from disclosure under applicable law.
 *   © 2016 IGT Global Solutions Corporation.
 *
 */

package com.mkowalski.nb.etl.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

//swagger doc available at http://localhost:8280
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mkowalski.nb.etl"))
                .paths(PathSelectors.any())
                .build().apiInfo(getApiInfo());
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .validatorUrl("/")
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "New Business",
                "New Business Description",
                "1.0",
                "",
                null,
                "",
                "",
                Collections.emptyList()
        );
    }
}
