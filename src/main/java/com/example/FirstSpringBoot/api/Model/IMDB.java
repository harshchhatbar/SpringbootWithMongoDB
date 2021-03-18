package com.example.FirstSpringBoot.api.Model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class IMDB {
    @Indexed(name = "ImdbRatingIndex")
    private Double rating;
    private Integer votes;
    private Integer id;

    public Double getRating() {
        return rating;
    }

    public Integer getVotes() {
        return votes;
    }

    public Integer getId() {
        return id;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IMDB{" +
                "rating=" + rating +
                ", votes=" + votes +
                ", id=" + id +
                '}';
    }
}
