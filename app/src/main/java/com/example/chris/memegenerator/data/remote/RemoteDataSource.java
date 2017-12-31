package com.example.chris.memegenerator.data.remote;

import com.example.chris.memegenerator.util.pojo.GoogleResponse;
import com.example.chris.memegenerator.util.pojo.Item;

import java.util.List;

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
    private static String baseURL, apiKey;
   // String baseURL, apiKey;
    
    public RemoteDataSource(String baseURL, String apiKey)
    {

        this.baseURL = baseURL;
        this.apiKey = apiKey;
    }
    
    public static Retrofit create()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                //add converter to parse the response
                .addConverterFactory(GsonConverterFactory.create())
                //add call adapter to convert the response to RxJava observable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        
        return retrofit;
    }
    public static Call<GoogleResponse> responseback(String mysearch, String date){
        Retrofit retrofit = create();
        RemoteService service = retrofit.create(RemoteService.class);
        return service.responseback(mysearch,date);
    }

/* Todo refrofit causing error
       public static Observable<List<GoogleResponse>> googleresult(String mysearch)
        {
            Retrofit retrofit = create();
            RemoteService remoteService = retrofit.create(RemoteService.class);
            return remoteService.googleresult(mysearch);
        }

*/
}
