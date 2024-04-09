package com.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator starCarRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p.path("/saraha/useraccounts/**")
                        .filters(f -> f.rewritePath("/saraha/useraccounts/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://useraccounts"))
                .route(p -> p.path("/saraha/messageGateway/**")
                        .filters(f -> f.rewritePath("/saraha/messageGateway/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://messageGateway")).build();
    }

}
