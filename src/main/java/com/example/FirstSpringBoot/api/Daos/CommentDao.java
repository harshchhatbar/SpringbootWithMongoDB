package com.example.FirstSpringBoot.api.Daos;

import com.example.FirstSpringBoot.api.Model.Comment;
import com.example.FirstSpringBoot.api.Repository.CommentRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Variable;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.expr;

@Component
public class CommentDao {

    private static String COLLNAME = "comments";
    private MongoTemplate mongoTemplate;
    private CommentRepository commentRepository;
    private MongoCollection<Document> commentCollection;

    @Autowired
    private CommentDao(MongoTemplate mongoTemplate, CommentRepository commentRepository)
    {
        this.mongoTemplate = mongoTemplate;
        this.commentRepository = commentRepository;
        commentCollection = mongoTemplate.getCollection(COLLNAME);
    }

    public List<Comment> getCommentsByUser(String name) {
        return commentRepository.findByname(name);
    }

    /*
        Return all the movies in which given user has commented.
        - I have used Lookup stage to grab documents from Movies
        Collection. using movies_id
        - I also unwind array of movies document.
    */
    public List<Document> getMovieByUserComment(String name) {

        List<Document> finaResult = new ArrayList<>();
        List<Bson> AggPipeline = new ArrayList<>();
        Bson MatchStage = Aggregates.match(new Document().append("name",name));
        AggPipeline.add(MatchStage);
        Bson LookUpStage = buildLookupStage();
        AggPipeline.add(LookUpStage);

        Bson FilteringStage = Aggregates.match(new Document()
                .append("moviesDoc.0", new Document()
                        .append("$exists",1)));

        AggPipeline.add(FilteringStage);

        Bson unwindStage = Aggregates.unwind("$moviesDoc");
        AggPipeline.add(unwindStage);
        commentCollection.aggregate(AggPipeline).into(finaResult);

        return finaResult;
    }

    /*
        This is a lookup stage of previous Method.
        Where local key is  "movie_id" and
        foreign key is "_id" from movies collection.
    */
    private Bson buildLookupStage() {
        List<Bson> AggPipeline = new ArrayList<>();

        List<Variable<String>> varList = new ArrayList<>();
        varList.add(new Variable<>("movieId","$movie_id"));

        Bson matchStage = Aggregates.match(expr(new Document("$eq", Arrays.asList("$_id","$$movieId"))));
        AggPipeline.add(matchStage);

        return Aggregates.lookup("movies",varList, AggPipeline, "moviesDoc");
    }
}
