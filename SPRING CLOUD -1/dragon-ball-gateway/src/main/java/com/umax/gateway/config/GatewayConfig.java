package com.umax.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayConfig {

    @Bean
    @Profile(("localhost-NoEureka"))
    public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/api/v1/dragonball/*").uri("http://localhost:8082"))
                .route(r->r.path("/api/v1/gameOfThrones/*").uri("http://localhost:8083"))
                .build();
    }

    @Bean
    @Profile(("localhost-Eureka"))
    public RouteLocator configLocalEureka(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/api/v1/dragonball/*").uri("lb://Lsoto-dragon-ball"))
                .route(r->r.path("/api/v1/gameOfThrones/*").uri("lb://Lsoto-game-of-thrones"))
                .build();
    }

/*    @Bean
    @Profile("localhost-circuitBraker")
    public RouteLocator configLocalBraker(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/dragonball/*")
                        .filters(f -> f.circuitBreaker(c -> c.setName("failLoverCB")
                                .setFallbackUri("forward:/api/v1/failover/characters")
                                .setRouteId("dbFailover")))
                        .uri("lb://Lsoto-dragon-ball"))
                .route(r -> r.path("/api/v1/gameOfThrones/*").uri("lb://Lsoto-game-of-thrones"))
                .route(r -> r.path("/api/v1/failover/*").uri("lb://Lsoto-dragon-ball-fail-lover"))
                .build();
    }*/

}
