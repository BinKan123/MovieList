package com.example.kanbi.movielist.Persistent;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by kanbi on 19/12/2017.
 */

public class Utils {
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null&&networkInfo.isConnectedOrConnecting();
    }

}
