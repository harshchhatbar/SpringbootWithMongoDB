package com.example.FirstSpringBoot.api.Model;

public class Critic {
    Double rating;
    Integer numReviews;
    Integer meter;

    @Override
    public String toString() {
        return "Critic{" +
                "rating=" + rating +
                ", numReviews=" + numReviews +
                ", meter=" + meter +
                '}';
    }

    public Double getRating() {
        return rating;
    }

    public Integer getNumReviews() {
        return numReviews;
    }

    public Integer getMeter() {
        return meter;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setNumReviews(Integer numReviews) {
        this.numReviews = numReviews;
    }

    public void setMeter(Integer meter) {
        this.meter = meter;
    }
}
