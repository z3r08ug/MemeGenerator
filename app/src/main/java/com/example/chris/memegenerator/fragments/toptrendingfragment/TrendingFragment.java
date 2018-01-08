package com.example.chris.memegenerator.fragments.toptrendingfragment;


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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * * Use the {@link TrendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrendingFragment extends Fragment {
    private static final String ARG_MEME = "memes";
    private static List<String> memes= new ArrayList<>();
    private RecyclerView topTrendingRv;
    private RecyclerAdapter recyclerAdapter;
    private String mMemes;
    public TrendingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param memesList  Parameter 1.

     * @return A new instance of fragment CurrentForeCastFragment.
     */
    public static TrendingFragment newInstance(List<String> memesList) {
        TrendingFragment fragment = new TrendingFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MEME, (Parcelable) memesList);
memes= memesList;

        // Log.d("Parm", "newInstance: "+param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMemes = getArguments().getString(ARG_MEME);


            //  Log.d("PAram", "onCreate: "+mParam1);

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_trending, container, false);
        topTrendingRv = view.findViewById(R.id.rvTopTrendingMemes);
        topTrendingRv.setLayoutManager(new GridLayoutManager(this.getActivity(),2));
        for (int i = 0; i < memes.size(); i++) {
            Log.d("Great", "onCreateView: "+memes.get(i));
        }
        loadTopTrending();
        return view;
    }


    private void loadTopTrending()
    {
        final List<Image> imageList= new ArrayList<>();
//        // memes.clear();
//        for (int i = 0; i < 10; i++) {
//            //  memes.add("https://loremflickr.com/320/240?random=3");
//            imageList.add(new Image("https://loremflickr.com/320/240"));
//        }
//        memes.clear();
//        for (int i = 0; i < 10; i++)
//            memes.add("http://techdows.com/wp-content/uploads/2010/07/Opera_logo2.png");
        for (int i = 0; i < memes.size(); i++) {
            imageList.add(new Image(memes.get(i)));
        }
//        recyclerAdapter = new RecyclerAdapter(memes);
//        topTrendingRv.setAdapter(recyclerAdapter);
        topTrendingRv.setAdapter(new RecyclerAdapter(imageList, new RecyclerAdapter.onMemeClickListner() {
            @Override
            public void onMemeClick(Image image) {
                Toast.makeText(getContext(), "Item Clicked"+image.getImageUrl(), Toast.LENGTH_LONG).show();
                MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(imageList, image.getImageUrl());
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame,memeSliderFragment).addToBackStack("Slider").commit();

            }
        }));
    }

}
