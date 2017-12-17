package com.example.kanbi.movielist.activity;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.kanbi.movielist.API.ApiClient;
import com.example.kanbi.movielist.API.ApiInterface;
import com.example.kanbi.movielist.Adapter.MovieAdapter;
import com.example.kanbi.movielist.Model.MovieModel;
import com.example.kanbi.movielist.API.MovieResponse;
import com.example.kanbi.movielist.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SearchView.SearchAutoComplete   searchAutoComplete;
    private ArrayList<MovieModel> movies;
    private ArrayList<String> itemlist;

    private final static String API_KEY= "9bbbedb3db58bc911856851ed68b0166";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(API_KEY.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please obtain API KEY first",Toast.LENGTH_LONG).show();
            return;
        }

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //populate list from api json
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call=apiService.getpopularity(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                int statusCode=response.code();
                movies=response.body().getResults();
                movieAdapter=new MovieAdapter(movies);
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    //searchView
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView=(SearchView) menuItem.getActionView();

        //autocomplete for searchview
        searchAutoComplete = (SearchView.SearchAutoComplete)     searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        searchAutoComplete.setDropDownBackgroundResource(R.color.cardview_light_background);
        // searchAutoComplete.setDropDownAnchor(R.id.action_search);
        searchAutoComplete.setThreshold(2);

        itemlist= new ArrayList<String>();
        itemlist.add("hotel");
        itemlist.add("flight");
        itemlist.add("summer");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,itemlist);
        searchAutoComplete.setAdapter(adapter);

        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub

                String searchString=(String)parent.getItemAtPosition(position);
                searchAutoComplete.setText(""+searchString);
                Toast.makeText(MainActivity.this, "you clicked "+searchString, Toast.LENGTH_LONG).show();

            }
        });

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                movieAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


}
