package com.example.offlinemovies.data.remote;

import com.example.offlinemovies.data.model.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {

    @GET("movie/popular")
    Call<ResponseMovie> loadMovies();
}
