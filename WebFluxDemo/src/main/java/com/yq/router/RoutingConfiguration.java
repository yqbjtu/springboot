
package com.yq.router;

import com.yq.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.http.MediaType;

@Configuration
public class RoutingConfiguration {
	
    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
        return route(GET("/api/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::getAll)
        		.andRoute(GET("/api/users/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUser)
        		.andRoute(POST("/api/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::createUser)
                .andRoute(PUT("/api/users/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::updateUser)
                .andRoute(DELETE("/api/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::deleteUser);
    }
    
}