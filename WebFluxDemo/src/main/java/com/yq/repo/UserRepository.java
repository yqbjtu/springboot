package com.yq.repo;

import com.yq.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
	public Mono<User> getUserById(Integer id);
	public Flux<User> getAllUsers();
	public Mono<User> createUser(Mono<User> user);
	public Mono<User> updateUser(Integer id, Mono<User> user);
	public Mono<Void> deleteUser(Integer id);
}
