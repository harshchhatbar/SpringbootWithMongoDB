package com.example.FirstSpringBoot.api.Repository;

import com.example.FirstSpringBoot.api.Model.Movies;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import static org.springframework.data.domain.Sort.Order.desc;

public class MovieCustomRepoImpl implements MovieCustomRepo{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MovieCustomRepoImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    /*
        To get an explanation of Any Query
    */

    public Document explainQuery(Query query)
    {
        Document explainDocument = new Document();
        explainDocument.put("find", "movies");
        explainDocument.put("filter",query.getQueryObject());
        Document doc = new Document("explain",explainDocument);
        return mongoTemplate.getDb().runCommand(doc);
    }

    @Override
    public List<Movies> getMoviesByImdbRating(double rating) {
        List<Movies> MovieList;

        Criteria criteria = Criteria.where("imdb.rating").gt(rating);

        Query query = new Query(criteria)
                .with(Sort.by(desc("imdb.rating"),desc("year")))
                .cursorBatchSize(10);

        query.fields().include("title","cast","language","year","imdb.rating").exclude("_id");
        MovieList = mongoTemplate.find(query, Movies.class);
        System.out.println(explainQuery(query).toJson(JsonWriterSettings
                .builder()
                .indent(true)
                .build()));
        return MovieList;
    }

//    public List<Movies> getMoviesByImdbRating(double rating) {
//        Criteria criteria = Criteria.where("imdb.rating").ne(null).ne("").gt(rating);
//        System.out.println("Started");
//        Query query = new Query(criteria);
//        List<Movies> hereMovies = mongoTemplate.find(query, Movies.class);
//        System.out.println("Completed");
//        return hereMovies;
//    }
//

}
