package com.example.FirstSpringBoot.api.Controller;

import com.example.FirstSpringBoot.api.Service.CommentService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/user/{username}")
    private List<Document> getMovieByUserComment(@PathVariable("username") String name)
    {
        return commentService.getMovieByUserComment(name);
    }

}
