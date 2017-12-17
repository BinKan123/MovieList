package com.example.kanbi.movielist.API;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kanbi on 14/12/2017.
 */
//URL="https://api.themoviedb.org/3/discover/movie?api_key=9bbbedb3db58bc911856851ed68b0166&sort_by=popularity.desc"
public class ApiClient {
    public static final String Movies_URL="https://api.themoviedb.org/3/discover/";
    private static Retrofit retrofit= null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Movies_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
