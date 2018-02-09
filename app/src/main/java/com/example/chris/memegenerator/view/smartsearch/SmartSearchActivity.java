package com.example.chris.memegenerator.view.smartsearch;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.chris.memegenerator.MemeApplication;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.data.remote.ImageRemoteDataSource;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.fragments.memesliderfrag.MemeSliderFragment;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.GridSpacingItemDecoration;
import com.example.chris.memegenerator.util.ImageObj;
import com.example.chris.memegenerator.util.adapters.RecyclerAdapter;
import com.example.chris.memegenerator.model.pojo.bingsearch.BingSearch;
import com.example.chris.memegenerator.view.home.MemeHomeActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmartSearchActivity extends AppCompatActivity implements SmartSearchContract.View
{
    @Inject
    SmartSearchPresenter presenter;
    private ArrayList<String> key;
    private List<ImageObj> posturl;
    private RecyclerView recyclerView;
    public static final String TAG = SmartSearchActivity.class.getSimpleName() + "_TAG";
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        MemeApplication.get(this).getSmartSearchComponent().inject(this);
        presenter.attachView(this);
        
        key = getIntent().getStringArrayListExtra("magic");
        if (key == null)
        {
            Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        recyclerView = findViewById(R.id.mainlayoutRecycleview);
        recyclerView.setLayoutManager(new GridLayoutManager(SmartSearchActivity.this, 2));
        
        int spanCount = 2; // 2 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
        
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        posturl = new ArrayList<>();
        
        for (int i = 0; i < key.size(); i++)
        {
            presenter.getBingResponse(key.get(i) + " memes");
        }
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
        MemeApplication.get(this).clearSmartSearchComponent();
    }
    
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(this, MemeHomeActivity.class));
    }
    
    @Override
    public void showError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void setBingResponse(List<String> memes)
    {
        for (int i = 0; i < memes.size(); i++)
            posturl.add(new ImageObj(memes.get(i)));
        
        recyclerView.setAdapter(new RecyclerAdapter(posturl, new RecyclerAdapter.onMemeClickListner()
        {
            @Override
            public void onMemeClick(ImageObj imageObj, int position)
            {
                MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(posturl, position);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.searchFragmentFrame, memeSliderFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }));
    }
    
    @Override
    public void showProgress(String progress)
    {
        Toast.makeText(this, progress, Toast.LENGTH_SHORT).show();
    }
}
