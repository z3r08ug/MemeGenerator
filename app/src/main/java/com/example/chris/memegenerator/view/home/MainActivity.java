package com.example.chris.memegenerator.view.home;

import android.content.Intent;
import android.content.Context;
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

import com.example.chris.memegenerator.fragments.toptrendingfragment.TrendingFragment;
import com.example.chris.memegenerator.model.pojo.keywordfinder.Keywords;
import com.example.chris.memegenerator.MemeApplication;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.util.handlers.FacebookHandler;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.adapters.RecyclerAdapter2;
import com.example.chris.memegenerator.view.createMeme.CreateMemeActivity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MemeHomeContract.View, FacebookHandler.FacebookLoginListener
{
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    @Inject
    MemeHomePresenter presenter;
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
//        MemeApplication.get(this).getMemeHomeComponent().inject(this);
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
            case R.id.itemCreate:
                startActivity(new Intent(this, CreateMemeActivity.class));
                break;
        }
        return true;
    }
    @Override
    public void showError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
    
    
    @Override
    public void setKeywords(Keywords keywords)
    {
    
    }
    
    @Override
    public void setBingResponseTrending(List<String> memes)
    {
        TrendingFragment.newInstance(memes);
    }
    
    @Override
    public void setBingResponseInterests(List<String> memes)
    {
    
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
   
    //This function will run on successful Facebook login
    @Override
    public void onSuccess()
    {
        //TODO Do something after the user successfully logs into Facebook!
    }
}
