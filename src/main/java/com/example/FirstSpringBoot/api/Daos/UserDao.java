package com.example.FirstSpringBoot.api.Daos;

import com.example.FirstSpringBoot.api.Model.UserMflix;
import com.example.FirstSpringBoot.api.Repository.UserRepository;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.server.UID;
import java.util.List;

@Component
public class UserDao {

    MongoClient mongoClient;
    UserRepository userRepository;

    @Autowired
    private UserDao(UserRepository userRepository, MongoClient mongoClient) {
        this.userRepository = userRepository;
        this.mongoClient = mongoClient;
    }

    public List<UserMflix> getAllUser() {
//        System.out.println("Hello I m here in UserDao");
//        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }

    public UserMflix InsertUser(UserMflix user) {
        return userRepository.insert(user);
    }


    public List<UserMflix> findUser(String name) {
        return userRepository.findByname(name);
    }
}
