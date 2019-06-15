package com.yq.handler;

import com.yq.domain.User;
import com.yq.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import org.springframework.http.MediaType;


import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserHandler {
    @Autowired
    private UserRepository userRepository;
    
    /**
     * GET ALL Users
     */
    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<User> users = userRepository.getAllUsers();

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, User.class);
    }
    
    /**
     * GET a User by ID 
     */
    public Mono<ServerResponse> getUser(ServerRequest request) {
        Integer userId = Integer.valueOf(request.pathVariable("id"));

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<User> userMono = userRepository.getUserById(userId);
        return userMono
                .flatMap(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(user)))
                .switchIfEmpty(notFound);
    }
    
    /**
     * create a User
     */
    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        
        return userRepository.createUser(userMono)
                .flatMap(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(user)));
    }
    
    /**
     *    update a User
     */
    public Mono<ServerResponse> updateUser(ServerRequest request) {
        Integer userId = Integer.valueOf(request.pathVariable("id"));

        Mono<User> userMono = request.bodyToMono(User.class);

        Mono<User> responseMono = userRepository.updateUser(userId, userMono);

        return responseMono
                .flatMap(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(user)));
    }

    /**
     *    DELETE a User
     */
    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        Integer userId = Integer.valueOf(request.pathVariable("id"));
        
        // build response
        return ServerResponse.ok().build(userRepository.deleteUser(userId));
    }
}