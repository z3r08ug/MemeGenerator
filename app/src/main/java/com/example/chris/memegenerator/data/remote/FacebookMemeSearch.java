package com.example.chris.memegenerator.data.remote;

import android.util.Log;
import android.widget.Toast;

import com.example.chris.memegenerator.util.pojo.keywordfinder.Keywords;
import com.example.chris.memegenerator.MemeApplication;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.view.main.MainActivity;
import com.example.chris.memegenerator.util.Keyword;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 1/8/2018.
 */

public class FacebookMemeSearch {
    public static final String TAG = "FacebookMemeSearch";
    public static ArrayList<String> keywords = new ArrayList<String>();
    public static void KeyWordrestCall (final String phrase){

        Constants.whichCall(Constants.keyword);
        RemoteDataSource.KeyWordResponse(phrase)
                .enqueue(new Callback<Keywords>() {
                    @Override
                    public void onResponse(Call<Keywords> call, Response<Keywords> response) {
                        if (response.body() != null) {
                            String tag = "";
                            String word = "";
                            for (int i = 0; i < response.body().getText().size(); i++) {
                               // Log.d(TAG, "onResponse: this is i: " + i);
                                for (int j = 0; j < response.body().getText().get(i).size(); j++) {
                                   // Log.d(TAG, "onResponse: this is j: " + j);
                                    for (int k = 0; k < response.body().getText().get(i).get(j).size(); k++) {
                                       // Log.d(TAG, "onResponse: this is k: " + k);
                                        tag = response.body().getText().get(i).get(j).get(k).getTag();
                                        if (tag.equals("NOUN") || tag.equals("VERB"))
                                        {
                                            if (tag.equals("NOUN") && word != null && word.length() >0 && Character.isUpperCase(word.charAt(0))
                                                    && Character.isUpperCase(response.body().getText().get(i).get(j).get(k).getWord().charAt(0)))
                                            {
                                                String proper_name = word + " " + response.body().getText().get(i).get(j).get(k).getWord();
                                                keywords.remove(word);
                                                keywords.add(proper_name);
                                                Log.d(TAG, "onResponse: saving the proper_name "
                                                        + proper_name +
                                                        " tag: " + tag);
                                            }
                                            else
                                            {
                                                word = response.body().getText().get(i).get(j).get(k).getWord();
                                                keywords.add(word);
                                                Log.d(TAG, "onResponse: saving the word "
                                                        + word +
                                                        " tag: " + tag);
                                            }

                                        }

                                       // Log.d(TAG, "KewyWordRestCall: word " + response.body().getText().get(i).get(j).get(k).getWord()
                                             //   + "\ntag: " + response.body().getText().get(i).get(j).get(k).getTag());
                                        //logd(MainActivity.this, " word: " +
                                                     //   response.body().getText().get(i).get(j).get(k).getWord()
                                                    //    + "\ntag: " + response.body().getText().get(i).get(j).get(k).getTag()
                                               // , Toast.LENGTH_LONG).show();

                                       // Log.d(TAG, "onResponse: get tag " + tag );
                                    }
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Keywords> call, Throwable t) {
                    }
                });

    }
    public ArrayList<String> generateKeywords(ArrayList<Keyword> keywords)
    {
        ArrayList<String> keys = new ArrayList<String>();
        //let's search for proper names and make sure that's passed as a single string
        for(Keyword k: keywords)
        {

        }
        return keys;
    }
}
