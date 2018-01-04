package com.example.chris.memegenerator.data.remote;
import com.example.chris.memegenerator.util.pojo.bingsearch.BingSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
/**
 * Created by Ady on 1/3/2018.
 */
public interface BingSearchRemoteService {
    @Headers("Ocp-Apim-Subscription-Key: 55ddbb8f99874102a42437acebb79565")
    @GET("bing/v7.0/images/search?mkt=en")
    Call<BingSearch> BingResponse(@Query("q") String mysearch);
    //Call<BingSearch> BingResponse();
}