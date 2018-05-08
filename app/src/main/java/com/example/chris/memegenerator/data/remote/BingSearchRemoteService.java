package com.example.chris.memegenerator.data.remote;
import com.example.chris.memegenerator.model.pojo.bingsearch.BingSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
/**
 * Created by Ady on 1/3/2018.
 */


public interface BingSearchRemoteService
{
    @Headers("Ocp-Apim-Subscription-Key: e7e0b19756644009ac9adf50d767e5cb")
    @GET("bing/v7.0/images/search")
    Call<BingSearch> BingResponse(@Query("q") String mysearch);
    //Call<BingSearch> BingResponse();
    
    @Headers("Ocp-Apim-Subscription-Key: e7e0b19756644009ac9adf50d767e5cb")
    @GET("bing/v7.0/images/search")
    io.reactivex.Observable<BingSearch> getBingResponse(@Query("q") String mysearch);
    
    @Headers("Ocp-Apim-Subscription-Key: e7e0b19756644009ac9adf50d767e5cb")
    @GET("bing/v7.0/images/search")
    io.reactivex.Observable<BingSearch> getBingkeywordResponse(@Query("q") String mysearch,
                                                        @Query("count") Integer num);
    
    
}