package com.example.chris.memegenerator.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.fragments.memesliderfrag.MemeSliderFragment;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.Image;
import com.example.chris.memegenerator.util.RecyclerAdapter;
import com.example.chris.memegenerator.util.RecyclerAdapter2;
import com.example.chris.memegenerator.util.pojo.bingsearch.BingSearch;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity
{
    
    private ArrayList<String> key;
    private List<Image> posturl;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         final String GoogleSerachBaseUrl = "https://www.googleapis.com/customsearch/";
         final String API_KEY = "AIzaSyBgFi0vAWqYPVS7VvKxV5ZzPiDYcunr7Fo";
         final String KeyWordBaseUrl = "https://api.textgain.com/1/";
         final String BingSearchBaseUrl = "https://api.cognitive.microsoft.com/";
        RemoteDataSource remoteDataSource = new RemoteDataSource(GoogleSerachBaseUrl, API_KEY,KeyWordBaseUrl,BingSearchBaseUrl);
        key = getIntent().getStringArrayListExtra("magic");
        posturl = new ArrayList<>();
        Constants.setallFALSE();
        Constants.whichCall(Constants.bing);
        for (int i = 0; i < key.size() ; i++)
        {
            Log.d("TAG_POST", "onCreate: new Activity key "+ key.get(i));

            remoteDataSource.BingResponse(key.get(i) + " meme")
                    .enqueue(new Callback<BingSearch>()
                    {
                        @Override
                        public void onResponse(Call<BingSearch> call, Response<BingSearch> response)
                        {
                            if (response.body() != null){
                            if (response.body().getValue()!= null)
                            {
                                for (int i = 0; i < response.body().getValue().size(); i++)
                                {
                                    Log.d("TAG_POST", "onResponse: ");
                                    posturl.add(new Image(response.body().getValue().get(i).getThumbnailUrl()));
    
                                   // posturl.add(new Image(response.body().getValue().get(i).getThumbnailUrl()));

                                }
                                RecyclerView recyclerView = findViewById(R.id.mainlayoutRecycleview);
//                                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Main2Activity.this, 2, LinearLayoutManager.VERTICAL, false);
//                                RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
//                                recyclerView.setLayoutManager(layoutManager);
//                                recyclerView.setItemAnimator(itemAnimator);
//                                RecyclerAdapter2 recyclerAdapter2 = new RecyclerAdapter2(posturl);
//                                recyclerView.setAdapter(recyclerAdapter2);
                                recyclerView.setLayoutManager(new GridLayoutManager(Main2Activity.this, 2));
                                recyclerView.setAdapter(new RecyclerAdapter(posturl, new RecyclerAdapter.onMemeClickListner() {
                                    @Override
                                    public void onMemeClick(Image image, int position) {
                                        MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(posturl, position);
                                        //FragmentManager fragmentManager= getFragmentManager();
                                        //fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame,memeSliderFragment).addToBackStack("Slider").commit();
                                        FragmentManager fragmentManager = getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.searchFragmentFrame, memeSliderFragment);
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();
                                    }
                                }));
                            }

                            }

                        }

                        @Override
                        public void onFailure(Call<BingSearch> call, Throwable t)
                        {
                            Log.d("TAG_POST", "onFailure: Failed to make Bing Call");
                        }
                    });

        }
        
        
        
    }
}
