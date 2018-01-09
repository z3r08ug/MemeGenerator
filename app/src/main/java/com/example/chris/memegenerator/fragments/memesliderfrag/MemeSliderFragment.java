package com.example.chris.memegenerator.fragments.memesliderfrag;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.fragments.searchfragment.SearchMemeFragment;
import com.example.chris.memegenerator.util.Image;
import com.example.chris.memegenerator.util.MemeSliderAdapter;
import com.example.chris.memegenerator.view.main.MemeHomeActivity;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemeSliderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemeSliderFragment extends Fragment {
    
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    
    private List<Image> mParam1;
    private String mParam2;
    static int imageurl;
    static List<Image> imageList;
    public MemeSliderFragment() {
        // Required empty public constructor
    }
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemeSliderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemeSliderFragment newInstance(List<Image> param1, int param2) {
        MemeSliderFragment fragment = new MemeSliderFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        args.putString(ARG_PARAM2, String.valueOf(param2));
        imageList= param1;
        imageurl=param2;
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (List<Image>) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_meme_slider, container, false);

        Log.d("LEt", "onCreateView: "+imageList.size());
        for (int i = 0; i < imageList.size(); i++) {
            Log.d("LEt", "onCreateView: "+imageList.get(i).getImageUrl());
        }
        ViewPager viewPager = view.findViewById(R.id.imageSliderPager);
        MemeSliderAdapter memeSliderAdapter = new MemeSliderAdapter(getContext(), imageList, imageurl);
        Log.d("Rizwan", "onCreateView: "+imageurl);
    viewPager.setAdapter(memeSliderAdapter);
    viewPager.setCurrentItem(imageurl);

//        MemeSliderAdapter memeSliderAdapter = new MemeSliderAdapter(getContext(), imageList);
       // viewPager.setAdapter(memeSliderAdapter);
        
        return view;
    }
    
}
