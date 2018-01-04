package com.example.chris.memegenerator.fragments.interestfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.fragments.memesliderfrag.MemeSliderFragment;
import com.example.chris.memegenerator.util.Image;
import com.example.chris.memegenerator.util.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingInterestFragment extends Fragment {

    private List<String> memes;
    private RecyclerView rvYourInterestMeme;

    public TrendingInterestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_interest, container, false);
        memes=new ArrayList<>();

        rvYourInterestMeme = view.findViewById(R.id.rvInterestSelectedMeme);
        rvYourInterestMeme.setLayoutManager(new GridLayoutManager(this.getActivity(),2));
        loadInterestTrending();
        return view;
    }
    private void loadInterestTrending() {
        final List<Image> imageList= new ArrayList<>();
       // memes.clear();
        for (int i = 0; i < 10; i++) {
          //  memes.add("https://loremflickr.com/320/240?random=3");
            imageList.add(new Image("https://loremflickr.com/320/240"));
        }


       // RecyclerAdapter recyclerAdapter = new RecyclerAdapter(memes);
       // rvYourInterestMeme.setAdapter(recyclerAdapter);
      rvYourInterestMeme.setAdapter(new RecyclerAdapter(imageList, new RecyclerAdapter.onMemeClickListner() {
          @Override
          public void onMemeClick(Image image) {
              Toast.makeText(getContext(), "Item Clicked"+image.getImageUrl(), Toast.LENGTH_LONG).show();
              MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(imageList, image.getImageUrl());
              FragmentManager fragmentManager = getFragmentManager();
              fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame,memeSliderFragment).commit();

          }
      }));
    }



}
