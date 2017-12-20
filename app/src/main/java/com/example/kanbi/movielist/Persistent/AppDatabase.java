package com.example.kanbi.movielist.Persistent;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.kanbi.movielist.Model.MovieModel;

/**
 * Created by kanbi on 18/12/2017.
 */
@Database(entities = {MovieModel.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract movieDao movieDao();
}
