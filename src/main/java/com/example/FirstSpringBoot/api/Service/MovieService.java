package com.example.FirstSpringBoot.api.Service;

import com.example.FirstSpringBoot.api.Daos.MovieDao;
import com.example.FirstSpringBoot.api.Model.Movies;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieDao movieDao;

    public List<Movies> getAll()
    {
        return movieDao.getAll();
    }

    public List<Movies> getMoviesByTitle(String title) {
        return movieDao.getMoviesByTitle(title);
    }

    public List<Movies> getMovieByYearGT(int year){
        long stTime = System.nanoTime();
        List<Movies> moviesList = movieDao.getMoviesByRangeGTYear(year);
        long ExecTime = System.nanoTime() - stTime;
        System.out.println(ExecTime/1000000);
        return moviesList;
    }

    public List<Movies> getMovieByYearLT(int year){
        return movieDao.getMoviesByRangeLTYear(year);
    }

    public List<Movies> getMovieByYearIN(int year){
        return movieDao.getMoviesByRangeINYear(year);
    }

    public List<Movies> getMovieByYearBTW(int year1, int year2){
        long stTime = System.nanoTime();
        List<Movies> moviesList = movieDao.getMoviesByRangeBTWYear(year1, year2);
        long ExecTime = System.nanoTime() - stTime;
        System.out.println(ExecTime/1000000);
        return moviesList;
    }

    public List<IndexInfo> getIndex() {
        return movieDao.getIndexListOfColl();
    }

    public List<Movies> getMoviesByLanguage(String type, String... language )
    {
        return movieDao.getMoviesByLanguage(type, language);
    }

    public List<Movies> getMoviesByAwardsWin(int AwardNum)
    {
        return movieDao.getMoviesByAwardsWin(AwardNum);
    }

    public List<Movies> MixedQuery(int size,String... Lang)
    {
        return movieDao.MixedQuery(size,Lang);
    }

    public List<Movies> AwardWinAndNomination(int wins, int Nomination)
    {
        return movieDao.AwardWinAndNomination(wins,Nomination);
    }

    public List<Movies> TomatoesRating(Double viewer, Double critic, int givenYear)
    {
        return movieDao.TomatoesRating(viewer,critic,givenYear);
    }

    public List<Document> FinaMoviesInEachYear(String... CastList)
    {
        return movieDao.FinaMoviesInEachYear(CastList);
    }

}
