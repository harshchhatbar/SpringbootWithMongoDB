package com.example.FirstSpringBoot.api.Repository;

import com.example.FirstSpringBoot.api.Model.Movies;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movies, String>,MovieCustomRepo {
    List<Movies> findBytitle(String title);
    List<Movies> findByyearGreaterThan(int year);
    List<Movies> findByyear(int year);
    List<Movies> findByyearLessThan(int year);
    List<Movies> findByyearBetween(int year1, int year2);

    @Query(fields = "{ 'title':1, 'countries':1 }")
    List<Movies> findByCountriesIn(String... CountryList);

    @Query(fields = "{'title': 1, 'Tomatoes':1 }")
    List<Movies> findByTomatoes_Viewer_RatingGreaterThan(int rating);

}
