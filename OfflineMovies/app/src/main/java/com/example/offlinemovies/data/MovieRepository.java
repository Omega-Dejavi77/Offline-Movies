package com.example.offlinemovies.data;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.offlinemovies.app.MyApp;
import com.example.offlinemovies.data.local.MovieRoomDatabase;
import com.example.offlinemovies.data.local.dao.MovieDAO;
import com.example.offlinemovies.data.remote.ApiConstants;
import com.example.offlinemovies.data.remote.MovieApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private final MovieApiService movieApiService;
    private final MovieDAO movieDAO;

    public MovieRepository() {
        MovieRoomDatabase movieRoomDatabase = Room.databaseBuilder(
                MyApp.getContext(), MovieRoomDatabase.class, "db_movies").build();
        movieDAO = movieRoomDatabase.getMovieDAO();

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(new RequestInterceptor());
        OkHttpClient client = okHttpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL + ApiConstants.API_KEY)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApiService = retrofit.create(MovieApiService.class);
    }
}
