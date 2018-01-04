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
import android.view.View;
import android.widget.ToggleButton;

import com.example.chris.memegenerator.MemeApplication;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.util.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
        
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
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
//        memes.clear();
//        for (int i = 0; i < 10; i++)
//            memes.add("http://icons.iconarchive.com/icons/graphicloads/100-flat/256/home-icon.png");
//
//        recyclerAdapter = new RecyclerAdapter(memes);
//        recyclerView.setAdapter(recyclerAdapter);
    }

    private void loadTopTrending()
    {
//        memes.clear();
//        for (int i = 0; i < 10; i++)
//            memes.add("http://techdows.com/wp-content/uploads/2010/07/Opera_logo2.png");
//
//        recyclerAdapter = new RecyclerAdapter(memes);
//        recyclerView.setAdapter(recyclerAdapter);
    }


    public void newActivity(View view) {
        Intent intent = new Intent(this, MemeHomeActivity.class);
        startActivity(intent);
    }
}
