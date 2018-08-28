package com.yq.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by yangqian on 2018/8/7.
 */
@Configuration
public class WebFluxRoutes {
    public static final String RESULT = "flux";

    @Bean(name = "flux-01")
    public RouterFunction<ServerResponse> webFluxGet() {
        Mono<String> date = Mono.just(RESULT);
        return RouterFunctions.route(RequestPredicates.path("/p1/flux/get"), request -> ServerResponse.ok().body(date, String.class));
    }
}
