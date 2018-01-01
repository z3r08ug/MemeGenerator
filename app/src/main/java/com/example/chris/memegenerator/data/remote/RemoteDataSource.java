package com.example.chris.memegenerator.data.remote;

import com.example.Keywords;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.pojo.googleserach.GoogleResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 11/29/2017.
 */

public class RemoteDataSource
{
    private static String GoogleSerachBaseUrl,KeyWordSerachBaseUrl, apiKey;
   // String GoogleSerachBaseUrl, apiKey;

    public RemoteDataSource(String GoogleSerachbaseUrl, String apiKey,String keyWordSerachBaseUrl)
    {

        this.GoogleSerachBaseUrl = GoogleSerachbaseUrl;
        this.apiKey = apiKey;
        this.KeyWordSerachBaseUrl = keyWordSerachBaseUrl;
    }



    public static Retrofit create()
    {
        String baseurl;
        if(Constants.isGoogle)
            baseurl = GoogleSerachBaseUrl;
        else
            baseurl =KeyWordSerachBaseUrl;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                //add converter to parse the response
                .addConverterFactory(GsonConverterFactory.create())
                //add call adapter to convert the response to RxJava observable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        
        return retrofit;
    }
    public static Call<GoogleResponse> GoogleResponse(String mysearch, String date, Integer page){

            Retrofit retrofit = create();
            GoogleSerachRemoteService service = retrofit.create(GoogleSerachRemoteService.class);
            return service.GoogleResponse(mysearch, date, page);

    }
    public static Call<Keywords> KeyWordResponse(String inputphrase){
        Retrofit retrofit = create();
        KeyWordSerachRemoteService service = retrofit.create(KeyWordSerachRemoteService.class);
        return service.KeyWordResponse(inputphrase);

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
