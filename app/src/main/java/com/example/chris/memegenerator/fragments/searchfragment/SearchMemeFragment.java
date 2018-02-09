package com.example.chris.memegenerator.fragments.searchfragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.data.remote.ImageRemoteDataSource;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.fragments.memesliderfrag.MemeSliderFragment;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.GridSpacingItemDecoration;
import com.example.chris.memegenerator.util.ImageObj;
import com.example.chris.memegenerator.util.adapters.RecyclerAdapter;
import com.example.chris.memegenerator.model.pojo.bingsearch.BingSearch;
import com.example.chris.memegenerator.model.pojo.bingsearch.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchMemeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchMemeFragment extends Fragment
{
    public static final String TAG = SearchMemeFragment.class.getSimpleName() + "_TAG";
    private List<String> memes;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rvSearchMeme;
    private List<ImageObj> imageObjList;
    private FloatingActionButton btnSearch;
    
    
    public SearchMemeFragment() {
        // Required empty public constructor
    }
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchMemeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchMemeFragment newInstance(List<String> param1, String param2)
    {
        SearchMemeFragment fragment = new SearchMemeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_search_meme, container, false);
        Button btnsearch = view.findViewById(R.id.btnSearchMemes);
        final EditText etSearch = view.findViewById(R.id.evSearch);
        memes=new ArrayList<>();
        rvSearchMeme = view.findViewById(R.id.rvSearchMeme);
        rvSearchMeme.setLayoutManager(new GridLayoutManager(this.getActivity(),2));
        int spanCount = 2; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
        rvSearchMeme.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getSearch =etSearch.getText().toString() + " memes";
                InputMethodManager imm = (InputMethodManager) getContext().
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                loadInterestTrending(getSearch);
            }
        });
        return view;
    }
    
    
    private void loadInterestTrending(String search)
    {
        Log.d(TAG, "loadInterestTrending: "+search);
        final List<ImageObj>memeUrlList= new ArrayList<>();
        ImageRemoteDataSource.getBingMemeResponse(search).enqueue(new Callback<BingSearch>()
        {
            @Override
            public void onResponse(Call<BingSearch> call, Response<BingSearch> response) {
                List<Value> valueLsit = response.body().getValue();
                for (int i = 0; i < valueLsit.size(); i++)
                {
                    memeUrlList.add(new ImageObj(valueLsit.get(i).getThumbnailUrl()));
                }
                for (int j = 0; j < memeUrlList.size(); j++)
                {
                    Log.d("TTT", "onResponse: "+memeUrlList.get(j));
                }
                rvSearchMeme.setAdapter(new RecyclerAdapter(memeUrlList, new RecyclerAdapter.onMemeClickListner() {
                    @Override
                    public void onMemeClick(ImageObj imageObj, int position) {
                        MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(memeUrlList, position);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame,memeSliderFragment, "Slider").addToBackStack("Slider").commit();
                        
                    }
                }));
            }
            
            @Override
            public void onFailure(Call<BingSearch> call, Throwable t)
            {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
    
}
