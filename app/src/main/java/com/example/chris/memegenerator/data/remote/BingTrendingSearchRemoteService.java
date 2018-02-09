package com.example.chris.memegenerator.data.remote;

import com.example.chris.memegenerator.model.pojo.bingsearch.BingSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Ady on 1/4/2018.
 */

public interface BingTrendingSearchRemoteService {
    @Headers("Ocp-Apim-Subscription-Key: 1a4b30846ee04b22942cb96ae7f52250")
    @GET("bing/v7.0/images/search/trending")
    Call<BingSearch> BingTrendingResponse(@Query("q") String mysearch);
}
