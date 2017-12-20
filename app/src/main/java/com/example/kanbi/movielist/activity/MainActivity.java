package com.example.kanbi.movielist.activity;

import android.app.SearchManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Toast;

import com.example.kanbi.movielist.API.ApiClient;
import com.example.kanbi.movielist.API.ApiInterface;
import com.example.kanbi.movielist.Adapter.MovieAdapter;
import com.example.kanbi.movielist.Model.MovieModel;
import com.example.kanbi.movielist.API.MovieResponse;
import com.example.kanbi.movielist.Persistent.AppDatabase;
import com.example.kanbi.movielist.Persistent.Utils;
import com.example.kanbi.movielist.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    AppDatabase appDatabase;

    private final static String API_KEY= "9bbbedb3db58bc911856851ed68b0166";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //room persistent
        appDatabase= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Movies")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        //check if API KEY is all right
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain API KEY first", Toast.LENGTH_LONG).show();
            return;
        }

        //define recyclerview
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //start with local
        getFeedFromLocal();

        // try with network data
        if (Utils.isNetworkAvailable(getApplicationContext())) {
            getFeedOnline();
        }
    }

    //searchView
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView=(SearchView) menuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                movieAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    //if network available, fetch json data, insert into local database, then show the list
    private void getFeedOnline() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getpopularity(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                //  int statusCode=response.code();  if needed for showing error type
                List<MovieModel> movies = response.body().getResults();

                // Update database
                appDatabase.movieDao().insertAll(movies);

                // Update visible list from Database.
                getFeedFromLocal();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                // if no network, try to get local data instead
                getFeedFromLocal();
            }
        });
    }

    //if no network, get data from local database
    private void getFeedFromLocal(){
        List<MovieModel> movies = appDatabase.movieDao().getAllMovies();

        movieAdapter = new MovieAdapter(movies);
        recyclerView.setAdapter(movieAdapter);
    }

}
