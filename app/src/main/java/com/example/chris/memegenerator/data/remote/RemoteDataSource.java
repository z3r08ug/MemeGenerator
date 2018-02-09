package com.example.chris.memegenerator.data.remote;

import android.os.Environment;
import android.util.Log;

import com.example.chris.memegenerator.model.pojo.keywordfinder.Keywords;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.model.pojo.bingsearch.BingSearch;
import com.example.chris.memegenerator.model.pojo.googleserach.GoogleResponse;

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

public class RemoteDataSource
{
    private static String GoogleSerachBaseUrl,KeyWordSerachBaseUrl, apiKey, BingSearchBaseurl;
    private static String TAG = "Remote Data Source";
    private static String baseurl;
    
    public RemoteDataSource(String GoogleSerachbaseUrl,
                            String apiKey,
                            String keyWordSerachBaseUrl,
                            String BingSearchBaseUrl)
    {
        
        this.GoogleSerachBaseUrl = GoogleSerachbaseUrl;
        this.apiKey = apiKey;
        this.KeyWordSerachBaseUrl = keyWordSerachBaseUrl;
        this.BingSearchBaseurl = BingSearchBaseUrl;
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
        baseurl = " ";
        if(Constants.isGoogle) {
            baseurl = GoogleSerachBaseUrl;
            Log.d(TAG, "create: Constant Google");
        }
        
        else if(Constants.isbing) {
            baseurl = BingSearchBaseurl;
            Log.d(TAG, "create: Constant Bing");
        }
        else if (Constants.istrending) {
            Log.d(TAG, "create: Constant Bing Trending");
            baseurl = BingSearchBaseurl;
        }
        else if(Constants.iskeyword) {
            baseurl = KeyWordSerachBaseUrl;
            Log.d(TAG, "create: Constant keyWord");
        }
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                //add converter to parse the response
                .client(httpClientConfig(loggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create())
                //add call adapter to convert the response to RxJava observable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        
        return retrofit;
    }
    
    public static Call<GoogleResponse> GoogleResponse(String mysearch, String date, Integer page){
        Log.d(TAG, "GoogleResponse: search " + mysearch + "baseurl " + baseurl);
        Retrofit retrofit = create();
        GoogleSearchRemoteService service = retrofit.create(GoogleSearchRemoteService.class);
        return service.GoogleResponse(mysearch, date, page);
    }
    
    public static Observable<Keywords> KeyWordResponse(String inputphrase){
        Retrofit retrofit = create();
        KeyWordSearchRemoteService service = retrofit.create(KeyWordSearchRemoteService.class);
        return service.KeyWordResponse(inputphrase);
    }
    
}