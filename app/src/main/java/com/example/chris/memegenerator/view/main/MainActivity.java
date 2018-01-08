package com.example.chris.memegenerator.view.main;

import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.Keywords;
import com.example.chris.memegenerator.MemeApplication;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.util.FacebookHandler;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.Image;
import com.example.chris.memegenerator.util.RecyclerAdapter2;
import com.example.chris.memegenerator.util.pojo.googleserach.Item;
import com.example.chris.memegenerator.view.createMeme.CreateMemeActivity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.facebook.login.widget.LoginButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainContract.View, FacebookHandler.FacebookLoginListener
{
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    @Inject
    MainPresenter presenter;
    private RecyclerView recyclerView;
    private RecyclerAdapter2 recyclerAdapter;
    private ToggleButton btnTopTrend;
    private ToggleButton btnInterestTrend;
    private List<String> trendingMemes, interestMemes, interests;
    
    
    private EditText etSerach;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "onCreate: ");
        MemeApplication.get(this).getMainComponent().inject(this);
        recyclerView = findViewById(R.id.rvMemeThumbnails);
        btnInterestTrend = findViewById(R.id.btnTrendingInterests);
        btnTopTrend = findViewById(R.id.btnTopTrending);
        Constants.setallFALSE();
        
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        presenter.attachView(this);
        trendingMemes = new ArrayList<>();
        interestMemes = new ArrayList<>();
        interests = new ArrayList<>();
        
        
        
        if (btnTopTrend.isChecked())
        {
            presenter.getBingSearch("cat trendingMemes", Constants.interestTrending);
            presenter.getBingSearch("trendingMemes", Constants.topTrending);
        }
        
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FacebookHandler.getInstance().onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        MemeApplication.get(this).clearMainComponent();
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
    public void setBingSearch(List<String> memes)
    {
        this.trendingMemes = memes;
        recyclerAdapter = new RecyclerAdapter2(trendingMemes);
        recyclerView.setAdapter(recyclerAdapter);
    }
    
    @Override
    public void setInterestBingSearch(List<String> memes) {
        this.interestMemes = memes;
        recyclerAdapter = new RecyclerAdapter2(interestMemes);
        recyclerView.setAdapter(recyclerAdapter);
    }
    
    
    @Override
    public void setInterestTrending()
    {
//       recyclerAdapter = new RecyclerAdapter(interestMemes);
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
                recyclerAdapter = new RecyclerAdapter2(trendingMemes);
                recyclerView.setAdapter(recyclerAdapter);
                //loadTopTrending();
                break;
            case R.id.btnTrendingInterests:
                btnTopTrend.setChecked(false);
                btnInterestTrend.setChecked(true);
                recyclerAdapter = new RecyclerAdapter2(interestMemes);
                recyclerView.setAdapter(recyclerAdapter);
//                loadInterestTrending();
                break;
            case R.id.btnCreateMeme:
                startActivity(new Intent(this, CreateMemeActivity.class));
                break;
        }
    }
    public void searchForMemes(View view)
    {
        etSerach = findViewById(R.id.etSearch);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etSerach.getWindowToken(), 0);
        KeyWordrestCall(etSerach.getText().toString());
    }
    public void KeyWordrestCall (final String phrase)
    {
        Constants.whichCall(Constants.keyword);
        RemoteDataSource.KeyWordResponse(phrase)
                .enqueue(new Callback<Keywords>() {
                    @Override
                    public void onResponse(Call<Keywords> call, Response<Keywords> response)
                    {
                        if (response.body() != null) {
                            String tag = "";
                            for (int i = 0; i < response.body().getText().size(); i++)
                            {
                                Log.d(TAG, "onResponse: this is i: " + i);
                                for (int j = 0; j < response.body().getText().get(i).size(); j++)
                                {
                                    Log.d(TAG, "onResponse: this is j: " + j);
                                    for (int k = 0; k < response.body().getText().get(i).get(j).size(); k++)
                                    {
                                        Log.d(TAG, "onResponse: this is k: " + k);
                                        Log.d(TAG, "onResponse: word "
                                                + response.body().getText().get(i).get(j).get(k).getWord() +
                                                " tag " + response.body().getText().get(i).get(j).get(k).getTag());
                                        Toast.makeText(MainActivity.this, " word: " +
                                                        response.body().getText().get(i).get(j).get(k).getWord()
                                                        + "\ntag: " + response.body().getText().get(i).get(j).get(k).getTag()
                                                , Toast.LENGTH_LONG).show();
                                        tag = response.body().getText().get(i).get(j).get(k).getTag();
                                        Log.d(TAG, "onResponse: get tag " + tag );
                                    }
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Keywords> call, Throwable t)
                    {
                    
                    }
                });
    }
    //This function will run on successful Facebook login
    @Override
    public void onSuccess()
    {
        //TODO Do something after the user successfully logs into Facebook!
    }
}
