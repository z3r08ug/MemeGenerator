package com.example.chris.memegenerator;

import android.app.Application;
import android.content.Context;

import com.example.chris.memegenerator.di.app.AppComponent;
import com.example.chris.memegenerator.di.app.AppModule;
import com.example.chris.memegenerator.di.app.DaggerAppComponent;
import com.example.chris.memegenerator.di.createMeme.CreateMemeComponent;
import com.example.chris.memegenerator.di.createMeme.CreateMemeModule;
import com.example.chris.memegenerator.di.memehome.MemeHomeComponent;
import com.example.chris.memegenerator.di.memehome.MemeHomeModule;
import com.example.chris.memegenerator.di.smartsearch.SmartSearchComponent;
import com.example.chris.memegenerator.di.smartsearch.SmartSearchModule;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.handlers.FavoritesHandler;

import timber.log.Timber;

/**
 * Created by chris on 12/9/2017.
 */

public class MemeApplication extends Application
{
    private static final String GoogleSerachBaseUrl = "https://www.googleapis.com/customsearch/";
    private static final String API_KEY = "69830c4d38b6259d7c9bd14adc09d2a1";
    private static final String KeyWordBaseUrl = "https://api.textgain.com/1/";
    private static final String BingSearchBaseUrl = "https://api.cognitive.microsoft.com/";
    private AppComponent appComponent;
    private MemeHomeComponent memeHomeComponent;
    private CreateMemeComponent createComponent;
    private SmartSearchComponent smartSearchComponent;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
    
        Timber.plant(new Timber.DebugTree());
        
        AppModule appModule = new AppModule(GoogleSerachBaseUrl, API_KEY,KeyWordBaseUrl,BingSearchBaseUrl, Constants.BASE_URL);
        
        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .build();
    }
    
    public static MemeApplication get(Context context)
    {
        return (MemeApplication) context.getApplicationContext();
    }
    
    public MemeHomeComponent getMemeHomeComponent()
    {
        memeHomeComponent = appComponent.add(new MemeHomeModule());
        return memeHomeComponent;
    }
    public void clearMainComponent()
    {
        memeHomeComponent = null;
    }
    public CreateMemeComponent getCreateComponent()
    {
        createComponent = appComponent.add(new CreateMemeModule());
        return createComponent;
    }
    public void clearCreateMemeComponent()
    {
        createComponent = null;
    }
    
    public SmartSearchComponent getSmartSearchComponent()
    {
        smartSearchComponent = appComponent.add(new SmartSearchModule());
        return smartSearchComponent;
    }
    public void clearSmartSearchComponent()
    {
        smartSearchComponent = null;
    }

    @Override
    public void onTerminate() {
        FavoritesHandler.commit();
        super.onTerminate();
    }
}
