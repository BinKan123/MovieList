package com.example.kanbi.movielist.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kanbi on 14/12/2017.
 */

public interface ApiInterface {
    @GET("movie?api_key=9bbbedb3db58bc911856851ed68b0166&sort_by=popularity.desc")
    Call<MovieResponse> getpopularity(@Query("api_key") String apiKey) ;

}
