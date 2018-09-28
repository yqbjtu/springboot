package com.yq.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yq.demo.entity.User;
@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    public User getByUserName(String username);

}
