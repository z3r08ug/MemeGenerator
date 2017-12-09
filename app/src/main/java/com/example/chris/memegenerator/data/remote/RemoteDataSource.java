package com.example.chris.memegenerator.data.remote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 11/29/2017.
 */

public class RemoteDataSource
{
    private static String baseUrl;
    String baseURL, apiKey;
    
    public RemoteDataSource(String baseURL, String apiKey)
    {
        this.baseURL = baseURL;
        this.apiKey = apiKey;
    }
    
    public static Retrofit create()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //add converter to parse the response
                .addConverterFactory(GsonConverterFactory.create())
                //add call adapter to convert the response to RxJava observable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        
        return retrofit;
    }

//        public static Observable<List<Book>> getBookList()
//        {
//            Retrofit retrofit = create();
//            RemoteService remoteService = retrofit.create(RemoteService.class);
//            return remoteService.getBooks();
//        }
}
