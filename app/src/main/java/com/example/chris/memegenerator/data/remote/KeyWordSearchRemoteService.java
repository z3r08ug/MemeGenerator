package com.example.chris.memegenerator.data.remote;

import com.example.chris.memegenerator.model.pojo.keywordfinder.Keywords;
import com.example.chris.memegenerator.util.Constants;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ady on 12/31/2017.
 */

public interface KeyWordSearchRemoteService
{

    @GET("tag?&lang=en&key=" + Constants.LANUAGE_PARSER_API_KEY)
    Observable<Keywords> KeyWordResponse(@Query("q") String mysearch);
}
