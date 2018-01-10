package com.example.chris.memegenerator.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nabeel on 1/8/2018.
 */

public class FavoritesHandler {

    private static boolean first = true;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Set<String> favorites;

    private static void initialize(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        favorites = sharedPreferences.getStringSet("favorites",null);
        first = false;
    }

    public static void commit(Context context) {
        if(!first)
            editor.commit();
    }

    public static Set<String> getFavorites(Context context) {
        if(first)initialize(context);
        return favorites;
    }

    public static void addFavorite(String url, Context context) {
        if(first)initialize(context);
        if(favorites==null)
            favorites = new HashSet<>();
        favorites.add(url);
    }

    public static void removeFavorite(String url, Context context) {
        if(first)initialize(context);
        if(favorites!=null) {
            if (favorites.contains(url))
                favorites.remove(url);
        }
        else
            favorites = new HashSet<>();
    }

    public static void removeAllFavorite(Context context) {
        if(first)initialize(context);
        favorites = new HashSet<>();
    }


}
