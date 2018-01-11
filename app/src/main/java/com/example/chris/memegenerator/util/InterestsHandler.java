package com.example.chris.memegenerator.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nabeel on 1/10/2018.
 */

public class InterestsHandler {

    private static boolean first = true;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Set<String> interests;

    private static void initialize(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences("settings",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        interests = sharedPreferences.getStringSet("interests",null);
        first = false;
    }

    public static void commit() {
        if(!first) {
            editor.putStringSet("interests", interests);
            editor.commit();
        }
    }

    public static Set<String> getInterests(Context context) {
        if(first)initialize(context);
        return interests;
    }

    public static void addInterest(String interest, Context context) {
        if(first)initialize(context);
        if(interests==null)
            interests=new HashSet<>();
        interests.add(interest);
        commit();
    }

    public static void removeInterest(String interest, Context context) {
        if(first)initialize(context);
        if(interests !=null) {
            if (interests.contains(interest))
                interests.remove(interest);
        }
        else
            interests = new HashSet<>();
        commit();
    }

    public static void removeAllInterest(Context context) {
        if(first)initialize(context);
        interests = new HashSet<>();
        commit();
    }

}