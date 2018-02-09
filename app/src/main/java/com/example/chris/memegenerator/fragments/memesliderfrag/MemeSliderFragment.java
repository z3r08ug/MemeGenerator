package com.example.chris.memegenerator.fragments.memesliderfrag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.util.ImageObj;
import com.example.chris.memegenerator.util.adapters.MemeSliderAdapter;
import com.example.chris.memegenerator.util.ZoomOutPageTransformer;

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
    
    
    private List<ImageObj> mParam1;
    private String mParam2;
    static int imageurl;
    static List<ImageObj> imageObjList;
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
    public static MemeSliderFragment newInstance(List<ImageObj> param1, int param2) {
        MemeSliderFragment fragment = new MemeSliderFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        args.putString(ARG_PARAM2, String.valueOf(param2));
        imageObjList = param1;
        imageurl=param2;
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (List<ImageObj>) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_meme_slider, container, false);

        Log.d("LEt", "onCreateView: "+ imageObjList.size());
        for (int i = 0; i < imageObjList.size(); i++) {
            Log.d("LEt", "onCreateView: "+ imageObjList.get(i).getImageUrl());
        }
        ViewPager viewPager = view.findViewById(R.id.imageSliderPager);
        MemeSliderAdapter memeSliderAdapter = new MemeSliderAdapter(this.getActivity(), imageObjList, imageurl);
        Log.d("Rizwan", "onCreateView: "+imageurl);
    viewPager.setAdapter(memeSliderAdapter);
    viewPager.setCurrentItem(imageurl);

        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        return view;
    }
    
}
