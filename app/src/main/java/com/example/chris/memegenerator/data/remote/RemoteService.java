package com.example.chris.memegenerator.data.remote;

import com.example.chris.memegenerator.util.pojo.GoogleResponse;
import com.example.chris.memegenerator.util.pojo.Item;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Admin on 11/29/2017.
 */

public interface RemoteService
{
/* todo Rxjava producing a error
  @GET("v1?key=AIzaSyCP2dP0cMCpQtw2v63bEtAYbiG7yB9R1P4&cx=005947202212711455101:s0hxc7549dg&searchtype=image&num=10&start=1")
  Observable<List<GoogleResponse>> googleresult(@Query("q") String mysearch);
  //Observable<List<GoogleResponse>> googleresult(@Path("q") String mysearch) ;
  */


  @GET("v1?key=AIzaSyCP2dP0cMCpQtw2v63bEtAYbiG7yB9R1P4&cx=005947202212711455101:s0hxc7549dg&searchtype=image&num=10&start=1")
  Call<GoogleResponse> responseback(@Query("q") String mysearch, @Query("dateRestrict") String date);
}