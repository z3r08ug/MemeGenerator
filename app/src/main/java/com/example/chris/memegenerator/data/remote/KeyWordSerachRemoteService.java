package com.example.chris.memegenerator.data.remote;

import com.example.chris.memegenerator.util.pojo.keywordfinder.Keywords;
import com.example.chris.memegenerator.util.pojo.googleserach.GoogleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ady on 12/31/2017.
 */

public interface KeyWordSerachRemoteService {

    @GET("tag?&lang=en&key=69830c4d38b6259d7c9bd14adc09d2a1")
    Call<Keywords> KeyWordResponse(@Query("q") String mysearch);
}
