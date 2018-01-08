package com.example.chris.memegenerator.data.remote;

import android.util.Log;

import com.example.Keywords;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.pojo.bingsearch.BingSearch;
import com.example.chris.memegenerator.util.pojo.googleserach.GoogleResponse;

import io.reactivex.Observable;
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
    // String GoogleSerachBaseUrl, apiKey;

    public RemoteDataSource(String GoogleSerachbaseUrl,
                            String apiKey,
                            String keyWordSerachBaseUrl,
                            String BingSearchBaseUrl )
    {

        this.GoogleSerachBaseUrl = GoogleSerachbaseUrl;
        this.apiKey = apiKey;
        this.KeyWordSerachBaseUrl = keyWordSerachBaseUrl;
        this.BingSearchBaseurl = BingSearchBaseUrl;
    }



    public static Retrofit create()
    {
        baseurl = " ";
        if(Constants.isGoogle) {
            baseurl = GoogleSerachBaseUrl;
            Log.d(TAG, "create: Constant Google");
        }
        else if(Constants.iskeyword) {
            baseurl = KeyWordSerachBaseUrl;
            Log.d(TAG, "create: Constant keyWord");
        }
        else if(Constants.isbing) {
            baseurl = BingSearchBaseurl;
            Log.d(TAG, "create: Constant Bing");
        }
        else if (Constants.istrending) {
            Log.d(TAG, "create: Constant Bing Trending");
            baseurl = BingSearchBaseurl;
        }
        Log.d(TAG, "create: base url "+ baseurl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                //add converter to parse the response
                .addConverterFactory(GsonConverterFactory.create())
                //add call adapter to convert the response to RxJava observable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        
        return retrofit;
    }
    public static Call<BingSearch> BingTrendingResponse(String search ){
        Log.d(TAG, "BingTrendingResponse: search: " + search+ " baseUrl "+ baseurl);
        Retrofit retrofit = create();
        BingTrendingSearchRemoteService service = retrofit.create(BingTrendingSearchRemoteService.class);
        return service.BingTrendingResponse(search);
    }
    public static Call<GoogleResponse> GoogleResponse(String mysearch, String date, Integer page){
        Log.d(TAG, "GoogleResponse: search " + mysearch + "baseurl " + baseurl);
            Retrofit retrofit = create();
            GoogleSerachRemoteService service = retrofit.create(GoogleSerachRemoteService.class);
            return service.GoogleResponse(mysearch, date, page);
    }
    public static Call<Keywords> KeyWordResponse(String inputphrase){
        Retrofit retrofit = create();
        KeyWordSerachRemoteService service = retrofit.create(KeyWordSerachRemoteService.class);
        return service.KeyWordResponse(inputphrase);
    }
    public static Call<BingSearch> BingResponse(String search ){
        Retrofit retrofit = create();
        BingSearchRemoteService service = retrofit.create(BingSearchRemoteService.class);
        return service.BingResponse(search);
    }
    
    public static Observable<BingSearch> getBingResponse(String search)
    {
        Retrofit retrofit = create();
        BingSearchRemoteService remoteService = retrofit.create(BingSearchRemoteService.class);
        return remoteService.getBingResponse(search);
    }

/* Todo refrofit causing error
       public static Observable<List<GoogleResponse>> googleresult(String mysearch)
        {
            Retrofit retrofit = create();
            GoogleSerachRemoteService remoteService = retrofit.create(GoogleSerachRemoteService.class);
            return remoteService.googleresult(mysearch);
        }

*/
}