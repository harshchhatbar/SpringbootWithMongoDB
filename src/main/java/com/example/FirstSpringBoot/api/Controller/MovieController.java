package com.example.FirstSpringBoot.api.Controller;

import com.example.FirstSpringBoot.api.Model.Movies;
import com.example.FirstSpringBoot.api.Repository.MovieRepository;
import com.example.FirstSpringBoot.api.Service.MovieService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/getAll")
    public String getAll()
    {
        movieService.getAll();
        return "completed";
    }

    @GetMapping("/title/{title_name}")
    public List<Movies> getMovieList(@PathVariable("title_name") String title) {
        return movieService.getMoviesByTitle(title);
    }

    @GetMapping("/year/gt/{year}")
    public List<Movies> getMovieListbyGTYear(@PathVariable int year) {
        return movieService.getMovieByYearGT(year);
    }

    @GetMapping("/year/in/{year}")
    public List<Movies> getMovieListbyINYear(@PathVariable int year) {
        return movieService.getMovieByYearIN(year);
    }

    @GetMapping("/year/lt/{year}")
    public List<Movies> getMovieListbyLTYear(@PathVariable int year) {
        return movieService.getMovieByYearLT(year);
    }

    @GetMapping("/year/btw/{year1}/{year2}")
    public List<Movies> getMovieListbyBTWYear(@PathVariable int year1, @PathVariable int year2) {
        return movieService.getMovieByYearBTW(year1, year2);
    }

    @GetMapping("/indexList")
    public List<IndexInfo> getIndexInfo() {
        return movieService.getIndex();
    }

    @GetMapping("/language/{type}")
    public List<Movies> getMoviesByLanguage(@PathVariable String type,
                                            @RequestBody String... language) {
        return movieService.getMoviesByLanguage(type, language);
    }

    @GetMapping("/AwardWin")
    public List<Movies> getMoviesByAwardsWin(@RequestBody int NumAwards) {
        return movieService.getMoviesByAwardsWin(NumAwards);
    }

    @GetMapping("/LangSize/{size}")
    public List<Movies> mixedQuery(@PathVariable int size, @RequestBody String... Lang) {
        return movieService.MixedQuery(size, Lang);
    }

    @GetMapping("/AwardWinNomination")
    public List<Movies> AwardWinAndNomination(@RequestParam int wins, @RequestParam int Nomination) {
        return movieService.AwardWinAndNomination(wins, Nomination);
    }

    @GetMapping("/TomatoesRating")
    public List<Movies> TomatoesRating(@RequestParam Double viewer,
                                       @RequestParam Double critic,
                                       @RequestParam int givenYear) {
        return movieService.TomatoesRating(viewer, critic, givenYear);
    }

    @GetMapping("/GroupByYear")
    public List<Document> FinaMoviesInEachYear(@RequestBody String... CastList) {
        return movieService.FinaMoviesInEachYear(CastList);
    }

    @GetMapping("/Country")
    public List<Movies> GetByCountry(@RequestBody String... CountryList) {
        System.out.println("yes");
        return movieRepository.findByCountriesIn(CountryList);
    }

    @GetMapping("/ViewerRating")
    public List<Movies> findByTomatoes_Viewer_RatingGreaterThan()
    {
        return movieRepository.findByTomatoes_Viewer_RatingGreaterThan(4);
    }

    @GetMapping("/imdbRating/{rating}")
    public List<Movies> getMoviesByImdbRating(@PathVariable("rating") double imdbRating){
        return movieRepository.getMoviesByImdbRating(imdbRating);
    }

}
