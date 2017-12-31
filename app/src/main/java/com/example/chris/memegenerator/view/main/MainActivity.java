package com.example.chris.memegenerator.view.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

import com.example.chris.memegenerator.MemeApplication;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.util.RecyclerAdapter;
import com.example.chris.memegenerator.util.pojo.GoogleResponse;
import com.example.chris.memegenerator.util.pojo.Item;
import com.example.chris.memegenerator.view.createMeme.CreateMemeActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainContract.View
{
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    
    @Inject
    MainPresenter presenter;
    
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ToggleButton btnTopTrend;
    private ToggleButton btnInterestTrend;
    private List<String> memes;
    private List<Item> result;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "onCreate: ");
        MemeApplication.get(this).getMainComponent().inject(this);

        result = new ArrayList<>();
        recyclerView = findViewById(R.id.rvMemeThumbnails);
        btnInterestTrend = findViewById(R.id.btnTrendingInterests);
        btnTopTrend = findViewById(R.id.btnTopTrending);
        
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this
                , 2, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        
        presenter.attachView(this);
    
        memes = new ArrayList<>();
        
        if (btnTopTrend.isChecked())
            loadTopTrending();
        else
            loadInterestTrending();
    
    }
    
    
    @Override
    protected void onStop()
    {
        super.onStop();
        MemeApplication.get(this).clearMapsComponent();
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.create:
                startActivity(new Intent(this, CreateMemeActivity.class));
                break;
        }
        return true;
    }
    
    @Override
    public void showError(String error)
    {
    
    }
    
    @Override
    public void setTopTrending()
    {
//        recyclerAdapter = new RecyclerAdapter(topMemes);
        recyclerView.setAdapter(recyclerAdapter);
    }
    
    @Override
    public void setInterestTrending()
    {
//        recyclerAdapter = new RecyclerAdapter(interestMemes);
        recyclerView.setAdapter(recyclerAdapter);
    }
    
    @Override
    public void showProgress(String progress)
    {
    
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        presenter.detachView();
    }
    
    public void onHandleClicks(View view)
    {
        switch (view.getId())
        {
            case R.id.btnTopTrending:
                btnTopTrend.setChecked(true);
                btnInterestTrend.setChecked(false);
                presenter.getTopTrending();
                loadTopTrending();
                break;
            case R.id.btnTrendingInterests:
                btnTopTrend.setChecked(false);
                btnInterestTrend.setChecked(true);
                presenter.getInterestTrending();
                loadInterestTrending();
                break;
        }
    }
    
    private void loadInterestTrending()
    {
        memes.clear();

        /*todo rxjava causinhg an error
        RemoteDataSource.googleresult("TrendingMemes")
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GoogleResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<GoogleResponse> googleResponses) {
                        Log.d(TAG, "onNext: Im here here");
                        memes.add(googleResponses.get(0).getItems().get(0).getPagemap().getCseImage().get(0).getSrc());
                        Log.d(TAG, "onNext: The link is "+ googleResponses.get(0).getItems().get(0).getPagemap().getCseImage().get(0).getSrc());


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+ e.getMessage().toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                */

       // for (int i = 0; i < 10; i++)
         //   memes.add("http://icons.iconarchive.com/icons/graphicloads/100-flat/256/home-icon.png");
        RemoteDataSource.responseback("Popular Memes",null)
                .enqueue(new Callback<GoogleResponse>() {
                    @Override
                    public void onResponse(Call<GoogleResponse> call, Response<GoogleResponse> response) {
                        result = response.body().getItems();
                        if (result != null) {
                            Log.d(TAG, "onResponse: " + result.get(0));
                            for (int i = 0; i < result.size(); i++) {
                                Log.d(TAG, "onResponse: " + result.size());
                                if (result.get(i).getPagemap() != null) {
                                    if (result.get(i).getPagemap().getCseImage() != null) {
                                        Log.d(TAG, "onResponse: " + i + " PageMap "
                                                + result.get(i).getPagemap().getCseImage().get(0).getSrc());
                                        Log.d(TAG, "onResponse: " + i + " PageMap Size "
                                                + result.get(i).getPagemap().getCseImage().size());
                                        memes.add(result.get(i).getPagemap().getCseImage().get(0).getSrc());
                                    } else {
                                        Log.d(TAG, "onResponse: PageMap is empty");
                                    }
                                }
                            }
                        }

                        recyclerAdapter = new RecyclerAdapter(memes);
                        recyclerView.setAdapter(recyclerAdapter);
                    }

                    @Override
                    public void onFailure(Call<GoogleResponse> call, Throwable t) {

                    }
                });

        

    }
    
    private void loadTopTrending()
    {
        memes.clear();
       // for (int i = 0; i < 10; i++)
         //   memes.add("http://techdows.com/wp-content/uploads/2010/07/Opera_logo2.png");
        RemoteDataSource.responseback("Memes","d30")
                .enqueue(new Callback<GoogleResponse>() {
                    @Override
                    public void onResponse(Call<GoogleResponse> call, Response<GoogleResponse> response) {
                        result = response.body().getItems();
                        if (result != null) {
                            Log.d(TAG, "onResponse: " + result.get(0));
                            for (int i = 0; i < result.size(); i++) {
                                Log.d(TAG, "onResponse: " + result.size());
                                if (result.get(i).getPagemap() != null) {
                                    if (result.get(i).getPagemap().getCseImage() != null) {
                                        Log.d(TAG, "onResponse: " + i + " PageMap "
                                                + result.get(i).getPagemap().getCseImage().get(0).getSrc());
                                        Log.d(TAG, "onResponse: " + i + " PageMap Size "
                                                + result.get(i).getPagemap().getCseImage().size());
                                        memes.add(result.get(i).getPagemap().getCseImage().get(0).getSrc());
                                    } else {
                                        Log.d(TAG, "onResponse: PageMap is empty");
                                    }
                                }
                            }
                        }

                        recyclerAdapter = new RecyclerAdapter(memes);
                        recyclerView.setAdapter(recyclerAdapter);
                    }

                    @Override
                    public void onFailure(Call<GoogleResponse> call, Throwable t) {

                    }
                });
        

    }


}
