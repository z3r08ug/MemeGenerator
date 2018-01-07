package com.example.chris.memegenerator.data.remote;

import com.example.chris.memegenerator.util.pojo.bingsearch.BingSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Ady on 1/4/2018.
 */

public interface BingTrendingSearchRemoteService {
    @Headers("Ocp-Apim-Subscription-Key: 55ddbb8f99874102a42437acebb79565")
    @GET("bing/v7.0/images/search/trending?mkt=en")
    Call<BingSearch> BingTrendingResponse(@Query("q") String mysearch);
}
