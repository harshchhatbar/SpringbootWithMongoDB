package com.example.FirstSpringBoot.api.Repository;


import com.example.FirstSpringBoot.api.Model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment,String> {

    List<Comment> findByname(String name);

}
