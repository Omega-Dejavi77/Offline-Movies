package com.example.offlinemovies.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.offlinemovies.data.MovieRepository;
import com.example.offlinemovies.data.local.entity.MovieEntity;
import com.example.offlinemovies.data.network.Resource;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieViewModel() {
        movieRepository = new MovieRepository();
    }

    public LiveData<Resource<List<MovieEntity>>> getMovies() {
        return movieRepository.getMovies();
    }
}
