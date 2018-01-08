package com.example.chris.memegenerator.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nabeel on 1/8/2018.
 */

public class FavoritesHandler {

    public static Set<String> getFavorites(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        Set<String> favorites = sharedPreferences.getStringSet("favorites",null);
        return favorites;
    }

    public static void addFavorite(String url, Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> favorites = sharedPreferences.getStringSet("favorites",null);
        if(favorites!=null)
            favorites.add(url);
        else {
            favorites = new HashSet<>();
            favorites.add(url);
        }
        editor.putStringSet("favorites",favorites);
        editor.commit();
    }

    public static void removeFavorite(String url, Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> favorites = sharedPreferences.getStringSet("favorites",null);
        if(favorites!=null) {
            if (favorites.contains(url))
                favorites.remove(url);
        }
        else
            favorites = new HashSet<>();
        editor.putStringSet("favorites",favorites);
        editor.commit();
    }

    public static void removeAllFavorite(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> favorites = new HashSet<>();
        editor.putStringSet("favorites",favorites);
        editor.commit();
    }

}
