package com.example.offlinemovies.data.remote;

import com.example.offlinemovies.data.model.ResponseMovie;

import retrofit2.Callback;
import retrofit2.http.GET;

public interface MovieApiService {

    @GET("movie/popular")
    Callback<ResponseMovie> loadPopularMovies();
}
