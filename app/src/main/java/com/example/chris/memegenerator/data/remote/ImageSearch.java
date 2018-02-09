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
