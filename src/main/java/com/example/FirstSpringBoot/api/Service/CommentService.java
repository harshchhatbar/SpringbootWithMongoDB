package com.example.FirstSpringBoot.api.Service;

import com.example.FirstSpringBoot.api.Daos.CommentDao;
import com.example.FirstSpringBoot.api.Model.Comment;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentService {

    @Autowired
    CommentDao commentDao;

    public Set<String> getMovieByUserComment(String name) {

        List<Document> DocsReqQuery = commentDao.getMovieByUserComment(name);

        //Set<String> keySet = DocsReqQuery.get(0).keySet();
        Set<String> giveMoviesByUserComment = new TreeSet<>();
        for(Document itr: DocsReqQuery)
        {
            Document movieDoc = (Document)itr.get("movieDoc");
            giveMoviesByUserComment.add((String)movieDoc.get("title"));
        }

        return giveMoviesByUserComment;
    }
}
