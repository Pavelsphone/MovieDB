package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String API_KEY = "YOUR_API_KEY_HERE"; // Замените на ваш API ключ
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchTopRatedMovies();
    }

    private void fetchTopRatedMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDbApi api = retrofit.create(TheMovieDbApi.class);
        Call<MovieResponse> call = api.getTopRatedMovies(API_KEY, "en-US", 1);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Movies received: " + response.body().getResults().size());
                    // Обработка полученных фильмов
                    for (Movie movie : response.body().getResults()) {
                        Log.d(TAG, "Movie: " + movie.getTitle() + " Rating: " + movie.getVoteAverage());
                    }
                } else {
                    Log.e(TAG, "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "Network request failed", t);
            }
        });
    }
}
