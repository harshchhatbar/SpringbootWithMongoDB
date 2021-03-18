package com.example.FirstSpringBoot.api.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.Nullable;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

@Document
public class Movies {

    @BsonProperty("_id")
    @BsonId
    @JsonIgnore
    private ObjectId oid;

    @BsonIgnore
    @JsonProperty
    @Id
    private String id;

    private String title;
    private String plot;
    private List<String> genres;
    private Integer runtime;
    private List<String> cast;

    //@Field("num_mflix_comments")
    private Integer num_mflix_comments;
    private String fullplot;
    private List<String> countries;
    private List<String> directors;

    private String rated;
    private Awards awards;

    //@Field("lastupdated")

    //@Indexed(name="HEREISYEAR")
    private String year;
    private IMDB imdb;

    private Date lastupdated;

    public Integer getNum_mflix_comments() {
        return num_mflix_comments;
    }

    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    String type;
    String poster;
    Integer metacritic;
    List<String> languages;
    tomatoes tomatoes;

    public ObjectId getOid() {
        return oid;
    }

    public void setOid(ObjectId oid) {
        this.oid = oid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getLanguages() {
        return languages;
    }


    public String getTitle() {
        return title;
    }

    public String getPlot() {
        return plot;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public List<String> getCast() {
        return cast;
    }

    public Integer getMflixComment() {
        return num_mflix_comments;
    }

    public String getFullplot() {
        return fullplot;
    }

    public List<String> getCountries() {
        return countries;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public String getRated() {
        return rated;
    }

    public Awards getAwards() {
        return awards;
    }

    public String getYear() {
        return year;
    }

    public IMDB getImdb() {
        return imdb;
    }

    public String getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }

    public Integer getMetacritic() {
        return metacritic;
    }

    public tomatoes getTomatoes() {
        return tomatoes;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public void setNum_mflix_comments(Integer num_mflix_comments) {
        this.num_mflix_comments = num_mflix_comments;
    }

    public void setFullplot(String fullplot) {
        this.fullplot = fullplot;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public void setAwards(Awards awards) {
        this.awards = awards;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImdb(IMDB imdb) {
        this.imdb = imdb;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setMetacritic(Integer metacritic) {
        this.metacritic = metacritic;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setTomatoes(com.example.FirstSpringBoot.api.Model.tomatoes tomatoes) {
        this.tomatoes = tomatoes;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id + "\"" +
                ", \"title\": \"" + title + "\"" +
                ", plot='" + plot + '\'' +
                ", genres=" + genres +
                ", runtime=" + runtime +
                ", cast=" + cast +
                ", MflixComment=" + num_mflix_comments +
                ", fullplot='" + fullplot + '\'' +
                ", countries=" + countries +
                ", directors=" + directors +
                ", rated='" + rated + '\'' +
                ", awards=" + awards +
                ", year=" + year +
                ", imdb=" + imdb +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                ", metacritic=" + metacritic +
                ", tomatoes=" + tomatoes +
                '}';
    }
}
