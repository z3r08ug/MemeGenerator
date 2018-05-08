package com.example.chris.memegenerator.data.remote;

import com.example.chris.memegenerator.model.pojo.bingsearch.BingSearch;
import com.example.chris.memegenerator.util.Constants;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by chris on 2/7/2018.
 */


/**
 * for right this second, 7 day trial, you can use either of these bing subsription keys (5/7/18)
 * Key 1: e7e0b19756644009ac9adf50d767e5cb
 Key 2: ba6d878f282644ebbfd20f9eac5a042c
 */
public interface ImageSearch
{
    @Headers("Ocp-Apim-Subscription-Key: "+ Constants.BING_KEY1)
    @GET("bing/v7.0/images/search")
    Observable<BingSearch> getBingTrendingResponse(@Query("q") String search);
    
    @Headers("Ocp-Apim-Subscription-Key: "+ Constants.BING_KEY2)
    @GET("bing/v7.0/images/search")
    Observable<BingSearch> getBingInterestResponse(@Query("q") String search);
    
    @Headers("Ocp-Apim-Subscription-Key: "+ Constants.BING_KEY1)
    @GET("bing/v7.0/images/search")
    Call<BingSearch> getBingMemeResponse(@Query("q") String search);
    
    @Headers("Ocp-Apim-Subscription-Key: "+ Constants.BING_KEY2)
    @GET("bing/v7.0/images/search")
    Observable<BingSearch> getBingPicResponse(@Query("q") String search);
}
