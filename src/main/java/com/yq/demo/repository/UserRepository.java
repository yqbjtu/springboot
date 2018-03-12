package com.yq.demo.repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import org.springframework.data.repository.CrudRepository;

import com.yq.demo.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

}