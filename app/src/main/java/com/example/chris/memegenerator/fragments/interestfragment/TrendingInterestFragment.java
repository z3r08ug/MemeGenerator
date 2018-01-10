package com.example.chris.memegenerator.fragments.interestfragment;


import android.os.Bundle;
import android.os.Handler;
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
import com.example.chris.memegenerator.fragments.toptrendingfragment.TrendingFragment;
import com.example.chris.memegenerator.util.GridSpacingItemDecoration;
import com.example.chris.memegenerator.util.Image;
import com.example.chris.memegenerator.util.RecyclerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingInterestFragment extends Fragment {

    static List<String> memesList;
    String TAG = "Fragment interest";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    // private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private RecyclerView interestRv;
    // private String mParam2;


    public TrendingInterestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     *               //     * @param param2 Parameter 2.
     * @return A new instance of fragment TrendingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrendingInterestFragment newInstance(List<String> param1) {
        Log.d("Interest_TAG", "newInstance: ");
        TrendingInterestFragment fragment = new TrendingInterestFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
//        args.putString(ARG_PARAM2, param2);
        memesList = param1;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: interest");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_interest, container, false);
        interestRv = view.findViewById(R.id.rvInterestSelectedMeme);
        interestRv.setLayoutManager(new GridLayoutManager(this.getActivity(), 2));
//        for (int i = 0; i < memesList.size(); i++) {
//            Log.d("Great", "onCreateView: "+memesList.get(i));
//        }
        int spanCount = 2; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
        interestRv.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                final List<Image> imageList = new ArrayList<>();
//        // memes.clear();
//        for (int i = 0; i < 10; i++) {
//            //  memes.add("https://loremflickr.com/320/240?random=3");
//            imageList.add(new Image("https://loremflickr.com/320/240"));
//        }
//        memes.clear();
//        for (int i = 0; i < 10; i++)
//            memes.add("http://techdows.com/wp-content/uploads/2010/07/Opera_logo2.png");

                // memesList.clear();
                Log.d(TAG, "run: interest");
                if (memesList != null) {
                    Log.d(TAG, "run: interests" + memesList);

                    for (int i = 0; i < memesList.size(); i++) {
                        imageList.add(new Image(memesList.get(i)));
                        Log.d("Great", "run: interests" + memesList.get(i));
                    }
                    Log.d(TAG, "run:thissssssssssssss sizeee interests" + imageList.size());
                }
                //        recyclerAdapter = new RecyclerAdapter(memes);
//        interestRv.setAdapter(recyclerAdapter);
//                interestRv.setAdapter(new RecyclerAdapter(imageList, new RecyclerAdapter.onMemeClickListner() {
//
//                    @Override
//                    public void onMemeClick(Image image) {
//                        Log.d("click", "onMemeClick: ");
//                        Toast.makeText(getContext(), "Item Clicked"+image.getImageUrl(), Toast.LENGTH_LONG).show();
//                        MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(imageList, image.getImageUrl());
//                        FragmentManager fragmentManager = getFragmentManager();
//                        fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame,memeSliderFragment).addToBackStack("Slider").commit();
//
//                    }
//                }));
                interestRv.setAdapter(new RecyclerAdapter(imageList, new RecyclerAdapter.onMemeClickListner() {
                    @Override
                    public void onMemeClick(Image image, int position) {
                     //   Toast.makeText(getContext(), "Item Clicked" + image.getImageUrl(), Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onMemeClick: "+position);
                        MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(imageList, position);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame,memeSliderFragment).addToBackStack("Slider").commit();
                    }
                }));
            }
        }, 2000);

        return view;
    }

//    private void loadMemes(final RecyclerView paramRv, final List<String> stringList) {
//
//        final List<Image> imageList = new ArrayList<>();
////        // memes.clear();
////        for (int i = 0; i < 10; i++) {
////            //  memes.add("https://loremflickr.com/320/240?random=3");
////            imageList.add(new Image("https://loremflickr.com/320/240"));
////        }
////        memes.clear();
////        for (int i = 0; i < 10; i++)
////            memes.add("http://techdows.com/wp-content/uploads/2010/07/Opera_logo2.png");
//
//        for (int i = 0; i < stringList.size(); i++) {
//            imageList.add(new Image(stringList.get(i)));
//            Log.d("Great", "run: " + stringList.get(i));
//
//        }
////        recyclerAdapter = new RecyclerAdapter(memes);
////        interestRv.setAdapter(recyclerAdapter);
//        paramRv.setAdapter(new RecyclerAdapter(imageList, new RecyclerAdapter.onMemeClickListner() {
//            @Override
//            public void onMemeClick(Image image) {
//                Log.d("click", "onMemeClick: ");
//                Toast.makeText(getContext(), "Item Clicked" + image.getImageUrl(), Toast.LENGTH_LONG).show();
//                MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(imageList, image.getImageUrl());
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame, memeSliderFragment).addToBackStack("Slider").commit();
//
//            }
//        }));
//    }
}
