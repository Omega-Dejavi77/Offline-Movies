package com.example.offlinemovies.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.offlinemovies.app.MyApp;
import com.example.offlinemovies.data.local.MovieRoomDatabase;
import com.example.offlinemovies.data.local.dao.MovieDAO;
import com.example.offlinemovies.data.local.entity.MovieEntity;
import com.example.offlinemovies.data.model.ResponseMovie;
import com.example.offlinemovies.data.network.NetworkBoundResource;
import com.example.offlinemovies.data.network.Resource;
import com.example.offlinemovies.data.remote.ApiConstants;
import com.example.offlinemovies.data.remote.MovieApiService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
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
                .baseUrl(ApiConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApiService = retrofit.create(MovieApiService.class);
    }

    public LiveData<Resource<List<MovieEntity>>> getMovies() {
        //Tipo que devuelve Room (BD local), Tipo que devuelve la API con Retrofit
        return new NetworkBoundResource<List<MovieEntity>, ResponseMovie>() {
            @Override
            protected void saveCallResult(@NonNull ResponseMovie item) {
                //guardar datos de la API a Room (local DB)
                movieDAO.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                //Datos que dispongamos en Room
                return movieDAO.loadMovies();
            }

            @NonNull
            @Override
            protected Call<ResponseMovie> createCall() {
                //API externa
                return movieApiService.loadMovies();
            }
        }.getAsLiveData();
    }
}
