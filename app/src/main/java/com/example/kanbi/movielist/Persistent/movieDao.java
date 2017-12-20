package com.example.kanbi.movielist.Persistent;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.kanbi.movielist.Model.MovieModel;

import java.util.List;

/**
 * Created by kanbi on 18/12/2017.
 */

@Dao
public interface movieDao {

    @Query("SELECT * FROM Movies ORDER BY popularity desc")
    List<MovieModel> getAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MovieModel> movies);

}
