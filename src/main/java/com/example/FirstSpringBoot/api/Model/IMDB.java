package com.example.FirstSpringBoot.api.Model;

public class IMDB {
    Double rating;
    Integer votes;
    Integer id;

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
