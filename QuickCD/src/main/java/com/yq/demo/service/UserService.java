package com.yq.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yq.demo.dao.UserJpaRepository;
import com.yq.demo.entity.User;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserJpaRepository userJpaRepo;

    public long userCount(){
        return userJpaRepo.count();
    }

    public List<User> getAllUser() {
        return userJpaRepo.findAll();
    }

    public User addUser(User user) {
        return userJpaRepo.save(user);
    }

    public void delUser(Long id) {
        userJpaRepo.delete(id);
    }

    public User updateUser(User user) {
        return userJpaRepo.save(user);
    }

    public User getUserByName(String username) {
        return userJpaRepo.getByUserName(username);
    }

    public User getUserByID(Long id) {
        return userJpaRepo.findOne(id);
    }

}
