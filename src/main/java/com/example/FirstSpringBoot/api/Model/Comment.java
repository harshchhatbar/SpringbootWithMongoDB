package com.example.FirstSpringBoot.api.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "comments")
public class Comment {

    @Id
    String id;
    String name;
    String email;

    @Field("movie_id")
    String movieId;

    String text;
    @Field("date")
    Date date;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", movieId='" + movieId + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
