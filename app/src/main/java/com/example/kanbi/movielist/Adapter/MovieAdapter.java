package com.example.kanbi.movielist.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kanbi.movielist.Model.MovieModel;
import com.example.kanbi.movielist.R;
import com.example.kanbi.movielist.activity.movie_details;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kanbi on 14/12/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements Filterable {

    private ArrayList<MovieModel> movieList;

    public MovieAdapter(ArrayList<MovieModel> List) {
        movieList = List;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView movieTitle;
        public ImageView poster;
        public TextView rating;

        public ViewHolder(View itemView) {
            super(itemView);

            movieTitle=(TextView) itemView.findViewById(R.id.title);
            poster=(ImageView) itemView.findViewById(R.id.poster);
            rating=(TextView) itemView.findViewById(R.id.ratingNr);
        }
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, final int i) {
        final MovieModel item = movieList.get(i);

        holder.movieTitle.setText(item.getTitle());
        holder.rating.setText(String.valueOf(item.getVote_average()) );

        final Context context = holder.itemView.getContext();
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+item.getPosterPath()).into(holder.poster);

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, movie_details.class);
                intent.putExtra("poster_key",item.getPosterPath());
                intent.putExtra("title_key",item.getTitle());
                intent.putExtra("release_key",item.getRelease_date());
                intent.putExtra("rating_key",String.valueOf(item.getVote_average()));
                intent.putExtra("adult_key",item.isAdult());
                intent.putExtra("overview_key",item.getOverview());
                context.startActivity(intent);

                //slide animation for intent transition
                ((Activity)  context).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


            }
    });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    //for search function
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                } else {

                    ArrayList<MovieModel> filteredList = new ArrayList<>();

                    for (MovieModel search : movieList) {

                        if (search.getTitle().toLowerCase().contains(charString)||(search.getTitle().toUpperCase().contains(charString) )) {

                            filteredList.add(search);
                        }
                    }
                   movieList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = movieList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieList = (ArrayList<MovieModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}

