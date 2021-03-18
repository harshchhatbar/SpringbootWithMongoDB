package com.example.FirstSpringBoot.api.Daos;

import com.example.FirstSpringBoot.api.Model.Movies;
import com.example.FirstSpringBoot.api.Repository.MovieRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.index.IndexInfo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static org.bson.codecs.configuration.CodecRegistries.*;
import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Component
public class MovieDao_1 {

    //public MoviesCodec moviesCodec;
    private static String COLLNAME="movies";
    private final MongoClient mongoClient;
    private final MongoTemplate mongoTemplate;
    private final MovieRepository movieRepository;

    @Autowired
    private MovieDao_1(MovieRepository movieRepository,
                     MongoClient mongoClient,
                     MongoTemplate mongoTemplate)
    {
        this.mongoClient = mongoClient;
        this.mongoTemplate = mongoTemplate;
        this.movieRepository = movieRepository;
    }

    /*
        To get list of indexes in movies collection.
    */

    public List<IndexInfo> getIndexListOfColl()
    {
        List<IndexInfo> indexInfos = mongoTemplate.indexOps(Movies.class).getIndexInfo();
        return indexInfos;
    }

    /*
        To store all the documents of movies collection inside the .json file.
    */
    public List<Movies> getAll()
    {
        List<Movies> moviesList;
        moviesList = movieRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(UNWRAP_ROOT_VALUE, true);
        File WriteInThisFile = new File("movies_try.json");

//        JSONObject json = new JSONObject(jsonString); // Convert text to object
//        System.out.println(json.toString(4)); // Print it with specified indentation
        try {
            JsonGenerator g = mapper.getFactory().createGenerator(new FileOutputStream(WriteInThisFile));
            //mapper.writeValue(g, "{");
            for(Movies movies:moviesList) {
                mapper.writer(new MinimalPrettyPrinter("\n")).writeValue(g,movies);
//                mapper.writeValue(g, movies);
//                mapper.writeValue(g, json);
//                System.out.println(json);
            }
            g.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    public List<Movies> getMoviesByTitle(String title)
    {
        List<Movies> movieList = movieRepository.findBytitle(title);
        return movieList;
    }

    public List<Movies> getMoviesByRangeGTYear(int year)
    {
        List<Movies> moviesList = movieRepository.findByyearGreaterThan(year);
        return moviesList;
    }

    public List<Movies> getMoviesByRangeLTYear(int year)
    {
        List<Movies> moviesList = movieRepository.findByyearLessThan(year);
        return moviesList;
    }

    /*
        To get an explanation of Any Query
    */

    public Document explainQuery(Query query)
    {
        Document explainDocument = new Document();
        explainDocument.put("find", COLLNAME);
        explainDocument.put("filter",query.getQueryObject());
        Document doc = new Document("explain",explainDocument);
        // Aa explain attribute ma query no object nakhi devano atle e query nu explanation aapi dese

        return mongoTemplate.getDb().runCommand(doc);
    }

    /*
        To get an explanation of Any Query (Overloaded method)
    */

    public Document explainQuery(Bson query)
    {
        Document explainDocument = new Document();
        explainDocument.put("find", COLLNAME);
        explainDocument.put("filter",query);

        Document doc = new Document("explain",explainDocument);

        return mongoTemplate.getDb().runCommand(doc);
    }

    /*
        Find all the list of movies which released in given year
    */
    public List<Movies> getMoviesByRangeINYear(int year)
    {
        Criteria criteria = Criteria.where("year").is(year);
        Query query = new Query().addCriteria(criteria);
//        Bson queryToFind = Filters.eq("year",year);
//        movieCollection.find(queryToFind,Movies.class);

        List<Movies> moviesList = mongoTemplate.find(query, Movies.class);

        System.out.println(explainQuery(query).toJson(JsonWriterSettings
                .builder()
                .indent(true)
                .build()));

        return moviesList;
    }

    /*
        Find all the list of movies which released in given interval of years
    */
    public List<Movies> getMoviesByRangeBTWYear(int year1, int year2)
    {
        Criteria criteria = Criteria.where("year").gt(year1).lt(year2);
        Query query = new Query().addCriteria(criteria);

        long stTime = System.nanoTime();

        List<Movies> moviesList = mongoTemplate.find(query, Movies.class);

        long ExecTime = System.nanoTime() - stTime;
        System.out.println(ExecTime/1000000);

        System.out.println(explainQuery(query).toJson(JsonWriterSettings
                .builder()
                .indent(true)
                .build()));

        return moviesList;
    }

    /*
        Find all the movies which has been made in list of given languages.
            Movie will be in result,
            1) For "in": only if movie has been made in at least one of the given languages.
            2) For "all": only if movie has been made in at least all the given languages.
            3) For "is": only if movie has been made in exactly given languages.
    */
    public List<Movies> getMoviesByLanguage(String type, String... Languages)
    {
        //System.out.println("here in dao");
        Criteria criteria;
        switch(type)
        {
            case "in":
            {
                criteria = Criteria.where("languages").in(Languages);
                break;
            }
            case "all":
            {
                criteria = Criteria.where("languages").all(Languages);
                break;
            }
            case "is":
            {
                criteria = Criteria.where("languages").is(Arrays.asList(Languages));
                break;
            }
            default:
            {
                System.out.println("Choose appropriate type parameter (either \"in\", \"all\", \"is\" )");
                return null;
            }
        }

        Query query = new Query().addCriteria(criteria);
        long stTime = System.nanoTime();
        List<Movies> moviesList = mongoTemplate.find(query, Movies.class);
        long ExecTime = System.nanoTime() - stTime;

        System.out.println("Execution Time: "+ExecTime/1000000);
        System.out.println(explainQuery(query).toJson(JsonWriterSettings
                .builder()
                .indent(true)
                .build()));

        return moviesList;
    }

    /*
        To do sorting on multiple field.

        Given list of all the movies who has won at least "numOfAwards".
        Then sort the result,
            first by awards.wins in descending order.
            then by year in ascending order.
            finally by title in descending order.
    */

    public List<Movies> getMoviesByAwardsWin(int numOfAwards)
    {
        Criteria criteria = new Criteria().where("awards.wins").gt(numOfAwards);
        // Sorting on multiple fields
        Query query = new Query().addCriteria(criteria)
                .with(Sort.by(
                        Arrays.asList(
                                desc("awards.wins"),
                                asc("year"),
                                desc("title") )
                        )
                );

        long stTime = System.nanoTime();
        List<Movies> moviesList = mongoTemplate.find(query, Movies.class);
        long ExecTime = System.nanoTime() - stTime;
        System.out.println(ExecTime/1000000);

        System.out.println(explainQuery(query).toJson(JsonWriterSettings
                .builder()
                .indent(true)
                .build()));

        return moviesList;
    }

    // Filters.in
    // Filters.all
    // Filters.eq
    // Filters.ne

    /*
           Give all the movies which was made in at least "size"
           number of languages AND was made in at least one of given
           List of "Lang".
           Projection on "languages", "title", "cast".
    */
    public List<Movies> MixedQuery(int size,String... Lang)
    {
        /*
                            using Spring Data MongoDB
        */
        // Below query will do collection search because of exists() clause.
        Criteria criteria = Criteria.where("languages."+size)
                .exists(true)
                .and("languages").in(Lang);

        Query query = new Query().addCriteria(criteria);
        List<Movies> moviesList = mongoTemplate.find(query, Movies.class);
        /*
              { "items": {"$elemMatch": {"price":  {"$gt": 50, "$lt": 55 }  }  }  }
              Criteria cry = new Criteria().where("items").elemMatch(new Criteria().where("price").gt(50).lt(55));
              Bson tryHere = Filters.elemMatch("items", Filters.and(gt("price",50),lt("price",55)) );
        */
        //
        System.out.println(explainQuery(query).toJson(JsonWriterSettings
                .builder()
                .indent(true)
                .build()));
        return moviesList;
    }

    // and, gt, gte using Filters.
    // projections using fields(include(),excludeId())
    // Sort using Sorts.by(Bson... )  and Sorts.ascending(), Sorts.descending()
    // into()

    /*
            Give list of movies who won at least "wins" number of
            awards AND who has at least "Nomination" number of nominations.
            Then Sort the result,
                First by "awards.win"
                    then by "awards.nominations"
            Projection on,
                "title","awards.wins","awards.nominations"
    */

    public List<Movies> AwardWinAndNomination(int wins, int Nomination)
    {
        List<Movies> moviesList1;

        /*
                            using Spring Data MongoDB
        */
        Criteria criteria = new Criteria().where("awards.wins").gte(wins)
                .and("awards.nominations").gte(Nomination);

        Query queryToDoWithCriteria = new Query()
                .addCriteria(criteria)
                .with(Sort.by(desc("awards.wins"),desc("awards.nominations")));

        queryToDoWithCriteria.fields().include("title","awards.wins","awards.nominations").exclude("_id");
        moviesList1 = mongoTemplate.find(queryToDoWithCriteria,Movies.class);
         /*
                            using Java MongoDB Driver
        */

        System.out.println(explainQuery(queryToDoWithCriteria).toJson(JsonWriterSettings
                .builder()
                .indent(true)
                .build()));

//        System.out.println(moviesList1);
        return moviesList1;
    }

    /*
     Find all the movies which
     has at least "viewerRating" in field "tomatoes.viewer.rating"
     AND has at least "criticRating" in field "tomatoes.critic.rating"
     AND was release after "givenYear"
     AND must be made in either "Hindi" or "English"

     Projection on "languages","year","tomatoes","title"
     Sort the result,
     First by tomatoes.viewer.rating in descending order
     then by year in ascending order
     */

    public List<Movies> TomatoesRating(Double viewerRating, Double criticRating, int givenYear)
    {
        /*
                            using Spring Data MongoDB
        */
        Criteria criteria = new Criteria().andOperator(Criteria.where("tomatoes.viewer.rating").gte(viewerRating)
                , Criteria.where("tomatoes.critic.rating").gte(criticRating)
                , Criteria.where("year").gte(givenYear)
                , Criteria.where("languages").in("Hindi","English")
        );

        Query query = new Query(criteria)
                .with(Sort.by(desc("tomatoes.viewer.rating"),asc("year")));

        // Projection.
        query.fields().include("title","languages","year");
        List<Movies> moviesList1 = mongoTemplate.find(query,Movies.class);

//        System.out.println(explainQuery(TomatoQuery).toJson(JsonWriterSettings
//                .builder()
//                .indent(true)
//                .build()));

        System.out.println();
        System.out.println();
        System.out.println(moviesList1.size()+" "+moviesList1.size());

        if(moviesList1.size() == moviesList1.size())
            System.out.println("Yes");

        return moviesList1;
    }

    /**
     Aggregation:
     Give "Year wise" list of documents and Highest Tomatoes Critic rating of documents which
     has given CastList as part of "cast" array field AND type is
     "movie".
     **/

    public List<Document> TryAggregationStage(String... CastList)
    {
        /*
                            Aggregation using Spring Data MongoDB
        */

        Criteria criteria = new Criteria().andOperator(Criteria.where("type").is("movie"),
                Criteria.where("cast").all(CastList));

        MatchOperation matchStage = Aggregation.match(criteria);
        GroupOperation grpStage = Aggregation.group("year").count().as("NumOfMovies")
                .push("title").as("ListOfTitle")
                .max("tomatoes.critic.rating").as("HighestRatedMovies");

        Aggregation agg = newAggregation(
                matchStage,
                grpStage,
                sort(Sort.Direction.DESC, "NumOfMovies","_id")
        );

        // Convert the result into Document.class
        AggregationResults<Document> aggResult = mongoTemplate.aggregate(agg,Movies.class, Document.class);
        List<Document> moviesList1 = aggResult.getMappedResults();

        return moviesList1;
    }
}
