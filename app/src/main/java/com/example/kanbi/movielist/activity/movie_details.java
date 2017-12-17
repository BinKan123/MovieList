package com.example.kanbi.movielist.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kanbi.movielist.R;
import com.squareup.picasso.Picasso;

public class movie_details extends AppCompatActivity {

    public ImageView poster;
    public TextView movieTitle;
    public TextView releaseDate;
    public TextView rating;
    public TextView isAdult;
    public TextView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Bundle extras = getIntent().getExtras();

        poster=(ImageView) findViewById(R.id.poster);
        movieTitle=(TextView) findViewById(R.id.title);
        releaseDate=(TextView) findViewById(R.id.releaseTime);
        rating=(TextView) findViewById(R.id.rating);
        isAdult=(TextView) findViewById(R.id.adult);
        overview=(TextView) findViewById(R.id.overview);

      /*  if (extras != null) {
            position = extras.getInt("item_key");
        }*/
        String postPathGet= extras.getString("poster_key");
        String movieTitleGet = extras.getString("title_key");
        String releaseDateGet = extras.getString("release_key");
        String ratingGet = extras.getString("rating_key");
        Boolean isAdultGet = extras.getBoolean("adult_key");
        String overviewGet= extras.getString("overview_key");


        Picasso.with(this).load("https://image.tmdb.org/t/p/w500"+postPathGet).into(poster);
        movieTitle.setText(movieTitleGet);
        releaseDate.setText(releaseDateGet);
        isAdult.setText(isAdultGet==false?"all ages":"18+");
        rating.setText(ratingGet);
        overview.setText(overviewGet);
    }
}


