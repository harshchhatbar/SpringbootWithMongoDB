package com.example.FirstSpringBoot.api.Repository;

import com.example.FirstSpringBoot.api.Model.UserMflix;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<UserMflix, String> {

    public List<UserMflix> findByname(String name);

    public List<UserMflix> findByemailId(String emailId);

}