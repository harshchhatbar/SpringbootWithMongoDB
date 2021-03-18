package com.example.FirstSpringBoot.api.Model;

import org.bson.*;
import org.bson.codecs.*;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class MoviesCodec implements CollectibleCodec<Movies> {

    private final Codec<Document> documentCodec;

    public MoviesCodec() {
        super();
        this.documentCodec = new DocumentCodec();
    }

    @Override
    public Movies generateIdIfAbsentFromDocument(Movies movies) {
        if(!documentHasId(movies))
            movies.setId(new ObjectId().toHexString());
        return movies;
    }

    @Override
    public Movies decode(BsonReader bsonReader, DecoderContext decoderContext) {
        Movies movies = new Movies();
        Document movieDoc = documentCodec.decode(bsonReader, decoderContext);

        if(movieDoc.getObjectId("_id") != null)
            movies.setId(movieDoc.getObjectId("_id").toHexString());
        movies.setTitle(movieDoc.getString("title"));
        movies.setFullplot(movieDoc.getString("fullplot"));
        movies.setCast((List<String>)movieDoc.get("cast"));
        movies.setAwards(movieDoc.get("awards",Awards.class));
        movies.setCountries((List<String>) movieDoc.get("countries"));
        movies.setGenres((List<String>) movieDoc.get("genres"));
        movies.setDirectors((List<String>) movieDoc.get("directors"));
        movies.setImdb(movieDoc.get("imdb",IMDB.class));
        movies.setLanguages((List<String>) movieDoc.get("languages"));
        movies.setMetacritic(movieDoc.getInteger("metacritic"));
        movies.setPlot(movieDoc.getString("plot"));
        movies.setNum_mflix_comments(movieDoc.getInteger("num_mflix_comments"));
        movies.setPoster(movieDoc.getString("poster"));
        movies.setRated(movieDoc.getString("rated"));
        movies.setRuntime(movieDoc.getInteger("runtime"));
        movies.setTomatoes(movieDoc.get("tomatoes",tomatoes.class));
        movies.setType(movieDoc.getString("type"));
        movies.setYear(movieDoc.getString("year"));
        return movies;
    }

    @Override
    public void encode(BsonWriter bsonWriter, Movies movies, EncoderContext encoderContext) {
        Document movieDoc = new Document();

        if (movies.getId() != null)
            movieDoc.append("_id", new ObjectId(movies.getId()));

        if (movies.getTitle() != null)
            movieDoc.append("title", movies.getTitle());

        if (movies.getPlot() != null)
            movieDoc.append("plot", movies.getPlot());

        if (movies.getGenres() != null)
            movieDoc.append("genres", movies.getGenres());

        if (movies.getRuntime() != null)
            movieDoc.append("runtime", movies.getRuntime());

        if (movies.getCast() != null)
            movieDoc.append("cast", movies.getCast());

        if (movies.getMflixComment() != null)
            movieDoc.append("num_mflix_comments", movies.getMflixComment());

        if (movies.getFullplot() != null)
            movieDoc.append("fullplot", movies.getFullplot());

        if (movies.getCountries() != null)
            movieDoc.append("countries", movies.getCountries());

        if (movies.getDirectors() != null)
            movieDoc.append("directors", movies.getDirectors());

        if (movies.getRated() != null)
            movieDoc.append("rated", movies.getRated());

        if (movies.getAwards() != null)
            movieDoc.append("awards", movies.getAwards());

        if (movies.getYear() != null)
            movieDoc.append("year", movies.getYear());

        if (movies.getImdb() != null)
            movieDoc.append("imdb", movies.getImdb());

        if (movies.getType() != null)
            movieDoc.append("type", movies.getType());

        if (movies.getPoster() != null)
            movieDoc.append("poster", movies.getPoster());

        if (movies.getMetacritic() != null)
            movieDoc.append("metacritic", movies.getMetacritic());

        if (movies.getLanguages() != null)
            movieDoc.append("languages", movies.getLanguages());

        if (movies.getTomatoes() != null)
            movieDoc.append("tomatoes", movies.getTomatoes());

        documentCodec.encode(bsonWriter, movieDoc, encoderContext);
    }

    @Override
    public boolean documentHasId(Movies movies) {
        return movies.getId() != null;
    }

    @Override
    public BsonValue getDocumentId(Movies movies) {
        if (!documentHasId(movies)) {
            throw new IllegalStateException("This document does not have an " + "_id");
        }

        return new BsonString(movies.getId());
    }

    @Override
    public Class<Movies> getEncoderClass() {
        return Movies.class;
    }
}
