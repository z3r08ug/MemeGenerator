package com.example.chris.memegenerator.fragments.toptrendingfragment;


import android.os.Bundle;
import android.os.Handler;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrendingFragment extends Fragment {
    static List<String> memesList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
   // private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private RecyclerView topTrendingRv;
    // private String mParam2;


    public TrendingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment TrendingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrendingFragment newInstance(List<String> param1) {
        TrendingFragment fragment = new TrendingFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
//        args.putString(ARG_PARAM2, param2);
        memesList=param1;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top_trending, container, false);
        topTrendingRv = view.findViewById(R.id.rvTopTrendingMemes);
        topTrendingRv.setLayoutManager(new GridLayoutManager(this.getActivity(),2));
//        for (int i = 0; i < memesList.size(); i++) {
//            Log.d("Great", "onCreateView: "+memesList.get(i));
//        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                final List<Image> imageList= new ArrayList<>();
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
                    Log.d("Great", "run: "+memesList.get(i));

                }
//        recyclerAdapter = new RecyclerAdapter(memes);
//        topTrendingRv.setAdapter(recyclerAdapter);
                topTrendingRv.setAdapter(new RecyclerAdapter(imageList, new RecyclerAdapter.onMemeClickListner() {

                    @Override
                    public void onMemeClick(Image image) {
                        Log.d("click", "onMemeClick: ");
                        Toast.makeText(getContext(), "Item Clicked"+image.getImageUrl(), Toast.LENGTH_LONG).show();
                        MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(imageList, image.getImageUrl());
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame,memeSliderFragment).addToBackStack("Slider").commit();

                    }
                }));
            }
        },2000);

        return view;
    }

    private void loadMemes(final RecyclerView paramRv, final List<String> stringList) {

                final List<Image> imageList= new ArrayList<>();
//        // memes.clear();
//        for (int i = 0; i < 10; i++) {
//            //  memes.add("https://loremflickr.com/320/240?random=3");
//            imageList.add(new Image("https://loremflickr.com/320/240"));
//        }
//        memes.clear();
//        for (int i = 0; i < 10; i++)
//            memes.add("http://techdows.com/wp-content/uploads/2010/07/Opera_logo2.png");

                for (int i = 0; i < stringList.size(); i++) {
                    imageList.add(new Image(stringList.get(i)));
                    Log.d("Great", "run: "+stringList.get(i));

                }
//        recyclerAdapter = new RecyclerAdapter(memes);
//        topTrendingRv.setAdapter(recyclerAdapter);
                paramRv.setAdapter(new RecyclerAdapter(imageList, new RecyclerAdapter.onMemeClickListner() {
                    @Override
                    public void onMemeClick(Image image) {
                        Log.d("click", "onMemeClick: ");
                        Toast.makeText(getContext(), "Item Clicked"+image.getImageUrl(), Toast.LENGTH_LONG).show();
                        MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(imageList, image.getImageUrl());
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame,memeSliderFragment).addToBackStack("Slider").commit();

                    }
                }));





    }

}
