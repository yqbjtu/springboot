package com.yq.repo.impl;

import com.yq.domain.User;
import com.yq.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private Map<Integer, User> userMap = new HashMap<>();

    @PostConstruct
    public void initIt() throws Exception {
        userMap.put(Integer.valueOf(1), new User(1, "ZhangSan@163.com", "ZhangSan", new Date()));
        userMap.put(Integer.valueOf(2), new User(2, "LiSi@qq.com", "LiSi", new Date()));
    }

    @Override
    public Mono<User> getUserById(Integer id) {
        return Mono.just(userMap.get(id));
    }

    @Override
    public Flux<User> getAllUsers() {
        return Flux.fromIterable(this.userMap.values());
    }

    @Override
    public Mono<User> createUser(Mono<User> monoUser) {
        return monoUser.doOnNext(user -> {
            userMap.put(user.getId(), user);
            log.info("createUser={}", user);
        });
    }

    @Override
    public Mono<User> updateUser(Integer id, Mono<User> monoUser) {
        Mono<User> userMono = monoUser.doOnNext(user -> {
            // reset user.Id
            user.setId(id);

            // do put
            userMap.put(id, user);

            log.info("updateUser={}", user);
        });

        return userMono;
    }

    @Override
    public Mono<Void> deleteUser(Integer id) {
        userMap.remove(id);
        return Mono.empty();
    }
}
