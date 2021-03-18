package com.example.FirstSpringBoot.api.Repository;

import com.example.FirstSpringBoot.api.Model.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;

public class MovieCustomRepoImpl implements MovieCustomRepo{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MovieCustomRepoImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Movies> getMoviesByImdbRating(double rating) {
        List<Movies> MovieList;

        Criteria criteria = Criteria.where("imdb.rating").gt(rating);

        Query query = new Query(criteria)
                .with(Sort.by(desc("imdb.rating"),asc("year")));

        query.fields().include("title","cast","language","year","imdb.rating").exclude("_id");
        MovieList = mongoTemplate.find(query, Movies.class);
        return MovieList;
    }
}
