// package com.example.demo.config;

// import io.swagger.v3.oas.models.Components;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.info.Contact;
// import io.swagger.v3.oas.models.info.License;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .components(new Components())
//                 .info(new Info()
//                         .title("Demo API")
//                         .description("This is the API documentation for the Demo project")
//                         .version("1.0.0")
//                         .contact(new Contact()
//                                 .name("Your Name")
//                                 .email("your.email@example.com")
//                                 .url("https://example.com"))
//                         .license(new License()
//                                 .name("Apache 2.0")
//                                 .url("http://springdoc.org")));
//     }
// }
package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // You need to change the port as per your server
                .servers(List.of(
                        new Server().url("https://9292.408procr.amypo.ai/")
                ));
        }
}