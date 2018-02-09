package com.example.chris.memegenerator.data.remote;

import android.os.Environment;

import com.example.chris.memegenerator.model.pojo.bingsearch.BingSearch;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 11/29/2017.
 */

public class ImageRemoteDataSource
{
    private static String TAG = ImageRemoteDataSource.class.getSimpleName() + "_TAG";
    private static String baseUrl;

    public ImageRemoteDataSource(String baseUrl)
    {

        this.baseUrl = baseUrl;
    }

    private static OkHttpClient httpClientConfig(HttpLoggingInterceptor interceptor)
    {
        File httpCacheDirectory = new File(Environment.getExternalStorageDirectory(), "HttpCache");// Here to facilitate the file directly on the SD Kagan catalog HttpCache in ， Generally put in context.getCacheDir() in

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        return new OkHttpClient.Builder().cache(cache).addInterceptor(interceptor).build();

    }

    private static HttpLoggingInterceptor loggingInterceptor()
    {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return  httpLoggingInterceptor;
    }

    public static Retrofit create()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //add converter to parse the response
                .client(httpClientConfig(loggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create())
                //add call adapter to convert the response to RxJava observable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        
        return retrofit;
    }
    
    public static Observable<BingSearch> getBingTrendingResponse(String search)
    {
        Retrofit retrofit = create();
        ImageSearch remoteService = retrofit.create(ImageSearch.class);
        return remoteService.getBingTrendingResponse(search);
    }
    
    public static Observable<BingSearch> getBingInterestsResponse(String search)
    {
        Retrofit retrofit = create();
        ImageSearch remoteService = retrofit.create(ImageSearch.class);
        return remoteService.getBingInterestResponse(search);
    }
    
    public static Call<BingSearch> getBingMemeResponse(String search)
    {
        Retrofit retrofit = create();
        ImageSearch remoteService = retrofit.create(ImageSearch.class);
        return remoteService.getBingMemeResponse(search);
    }
    
    public static Observable<BingSearch> getBingPicResponse(String search)
    {
        Retrofit retrofit = create();
        ImageSearch remoteService = retrofit.create(ImageSearch.class);
        return remoteService.getBingPicResponse(search);
    }
}