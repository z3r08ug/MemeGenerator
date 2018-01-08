package com.example.chris.memegenerator.fragments.toptrendingfragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.fragments.memesliderfrag.MemeSliderFragment;
import com.example.chris.memegenerator.util.Image;
import com.example.chris.memegenerator.util.RecyclerAdapter;
import com.example.chris.memegenerator.view.main.MainContract;
import com.example.chris.memegenerator.view.main.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class TrendingFragment extends Fragment implements MainContract.View {

    MainPresenter presenter;
    private static List<String> memes = new ArrayList<>();
    private RecyclerView topTrendingRv;


    private TopTrendingPresenter topTrendingPresenter;

    public TrendingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_trending, container, false);
        topTrendingRv = view.findViewById(R.id.rvTopTrendingMemes);
        topTrendingRv.setLayoutManager(new GridLayoutManager(this.getActivity(), 2));
        Log.d("Top", "onCreateView: ");
        presenter= new MainPresenter();
        presenter.attachView(this);
        presenter.getBingSearch("memes");

        //   loadTopTrending();
        return view;
    }


    @Override
    public void showError(String error) {

    }

    @Override
    public void setBingSearch(List<String> memesList) {
        final List<Image> imageList = new ArrayList<>();
//        // memes.clear();
//        for (int i = 0; i < 10; i++) {
//            //  memes.add("https://loremflickr.com/320/240?random=3");
//            imageList.add(new Image("https://loremflickr.com/320/240"));
//        }
//        memes.clear();
//        for (int i = 0; i < 10; i++)
//            memes.add("http://techdows.com/wp-content/uploads/2010/07/Opera_logo2.png");
        for (int i = 0; i < memesList.size(); i++) {
            imageList.add(new Image(memesList.get(i)));

        }
//        recyclerAdapter = new RecyclerAdapter(memes);
//        topTrendingRv.setAdapter(recyclerAdapter);
        topTrendingRv.setAdapter(new RecyclerAdapter(imageList, new RecyclerAdapter.onMemeClickListner() {
            @Override
            public void onMemeClick(Image image) {
                Log.d("click", "onMemeClick: ");
                Toast.makeText(getContext(), "Item Clicked" + image.getImageUrl(), Toast.LENGTH_LONG).show();
                MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(imageList, image.getImageUrl());
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame, memeSliderFragment).addToBackStack("Slider").commit();

            }
        }));
    }

    @Override
    public void setTopTrending() {

    }

    @Override
    public void setInterestTrending() {

    }

    @Override
    public void showProgress(String progress) {

    }
}
