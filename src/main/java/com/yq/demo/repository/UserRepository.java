package com.yq.demo.repository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
//findAll, findOne(ID)

import org.springframework.data.repository.CrudRepository;

import com.yq.demo.domain.User;


public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);

}