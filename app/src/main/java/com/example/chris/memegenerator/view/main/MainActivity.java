package com.example.chris.memegenerator.view.main;

import android.content.Intent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.example.Keywords;
import com.example.chris.memegenerator.MemeApplication;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.util.FacebookHandler;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.Image;
import com.example.chris.memegenerator.util.RecyclerAdapter;
import com.example.chris.memegenerator.util.pojo.bingsearch.BingSearch;
import com.example.chris.memegenerator.util.pojo.bingsearch.Value;
import com.example.chris.memegenerator.util.pojo.googleserach.GoogleResponse;
import com.example.chris.memegenerator.util.pojo.googleserach.Item;
import com.example.chris.memegenerator.view.createMeme.CreateMemeActivity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.facebook.login.widget.LoginButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
public class MainActivity extends AppCompatActivity implements MainContract.View, FacebookHandler.FacebookLoginListener
{
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    @Inject
    MainPresenter presenter;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ToggleButton btnTopTrend;
    private ToggleButton btnInterestTrend;
    private List<String> memes;
    private List<Item> result;
    Runnable mrunnable;
    List<Image> imageUrl;
    Handler mhandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                List<String> memesurl = (List<String>) msg.obj;
                for (int i = 0; i <memesurl.size() ; i++) {
                    memes.add(memesurl.get(i));
                   // Log.d(TAG, "handleMessage: memesurl "+memes.get(i));
                   // imageUrl.get(i).setImageUrl(memesurl.get(i));
                }
//                displayRecycleView();
            }
        }
    };
    public LoginButton fbLoginButton;
    private EditText etSerach;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "onCreate: ");
        //MemeApplication.get(this).getMainComponent().inject(this);
        result = new ArrayList<>();
        imageUrl = new ArrayList<>();
        recyclerView = findViewById(R.id.rvMemeThumbnails);
        btnInterestTrend = findViewById(R.id.btnTrendingInterests);
        btnTopTrend = findViewById(R.id.btnTopTrending);
        Constants.setallFALSE();
        //Register Facebook Login Button
        fbLoginButton = findViewById(R.id.facebook_login_button);
        FacebookHandler.getInstance().registerLoginButton(fbLoginButton,this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        presenter.attachView(this);
        memes = new ArrayList<>();
//       if (btnTopTrend.isChecked())
//           loadTopTrending();
//        else
//           loadInterestTrending();
        //BingSerach("memes");
       if (btnTopTrend.isChecked())
           loadTopTrending();
        else
           loadInterestTrending();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FacebookHandler.getInstance().onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        MemeApplication.get(this).clearMainComponent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.create:
                startActivity(new Intent(this, CreateMemeActivity.class));
                break;
        }
        return true;
    }
    @Override
    public void showError(String error)
    {
    }
    @Override
    public void setTopTrending()
    {
//        recyclerAdapter = new RecyclerAdapter(topMemes);
        recyclerView.setAdapter(recyclerAdapter);
    }
    
    @Override
    public void setBingSearch(List<String> memes)
    {
        this.memes = memes;
    }
    
    @Override
    public void setInterestTrending()
    {
//       recyclerAdapter = new RecyclerAdapter(interestMemes);
        recyclerView.setAdapter(recyclerAdapter);
    }
    @Override
    public void showProgress(String progress)
    {
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        presenter.detachView();
    }
////    public void onHandleClicks(View view)
////    {
////        switch (view.getId())
////        {
////            case R.id.btnTopTrending:
////                btnTopTrend.setChecked(true);
////                btnInterestTrend.setChecked(false);
////                presenter.getTopTrending();
////               // loadTopTrending();
////                break;
////            case R.id.btnTrendingInterests:
////                btnTopTrend.setChecked(false);
////                btnInterestTrend.setChecked(true);
////                presenter.getInterestTrending();
////                loadInterestTrending();
////                break;
////            case R.id.btnCreateMeme:
////                startActivity(new Intent(this, CreateMemeActivity.class));
////                break;
////        }
////    }
////    private void loadInterestTrending()
////    {
//////        memes.clear();
//////        for (int i = 0; i < 10; i++)
//////            memes.add("http://icons.iconarchive.com/icons/graphicloads/100-flat/256/home-icon.png");
//////
//////        recyclerAdapter = new RecyclerAdapter(memes);
//////        recyclerView.setAdapter(recyclerAdapter);
////    }
//
////    private void loadTopTrending()
////    {
////        memes.clear();
////        for (int i = 0; i < 10; i++)
////            memes.add("http://techdows.com/wp-content/uploads/2010/07/Opera_logo2.png");
////
////        recyclerAdapter = new RecyclerAdapter(memes);
////        recyclerView.setAdapter(recyclerAdapter);
////        memes.clear();
//////        /*todo rxjava causinhg an error
////        RemoteDataSource.googleresult("TrendingMemes")
////                .subscribeOn(Schedulers.io())
////                .subscribeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Observer<List<GoogleResponse>>() {
////                    @Override
////                    public void onSubscribe(Disposable d) {
////                    }
////                    @Override
////                    public void onNext(List<GoogleResponse> googleResponses) {
////                        Log.d(TAG, "onNext: Im here here");
////                        memes.add(googleResponses.get(0).getItems().get(0).getPagemap().getCseImage().get(0).getSrc());
////                        Log.d(TAG, "onNext: The link is "+ googleResponses.get(0).getItems().get(0).getPagemap().getCseImage().get(0).getSrc());
////                    }
////                    @Override
////                    public void onError(Throwable e) {
////                        Log.d(TAG, "onError: "+ e.getMessage().toString());
////                    }
////                    @Override
////                    public void onComplete() {
////                    }
////                });
//              //  */
//       // for (int i = 0; i < 10; i++)
//         //   memes.add("http://icons.iconarchive.com/icons/graphicloads/100-flat/256/home-icon.png");
//        //for (int i = 1; i <50 ; i=i+10) {
//          //  GoogleSerachCall("popular memes",null,i);
//        //}
////        BingSerach("popular memes");
////        recyclerAdapter = new RecyclerAdapter(memes);
////        recyclerView.setAdapter(recyclerAdapter);
////    }
//    private void loadTopTrending()
//    {
    public void onHandleClicks(View view)
    {
        switch (view.getId())
        {
            case R.id.btnTopTrending:
                btnTopTrend.setChecked(true);
                btnInterestTrend.setChecked(false);
                presenter.getBingSearch("memes");
                loadTopTrending();
                break;
            case R.id.btnTrendingInterests:
                btnTopTrend.setChecked(false);
                btnInterestTrend.setChecked(true);
                presenter.getInterestTrending();
                loadInterestTrending();
                break;
            case R.id.btnCreateMeme:
                startActivity(new Intent(this, CreateMemeActivity.class));
                break;
        }
    }
    //private void loadInterestTrending()
   // {
//        memes.clear();
//        for (int i = 0; i < 10; i++)
//            memes.add("http://icons.iconarchive.com/icons/graphicloads/100-flat/256/home-icon.png");
//
//        recyclerAdapter = new RecyclerAdapter(memes);
//        recyclerView.setAdapter(recyclerAdapter);
   // }

    private void loadInterestTrending()
    {
//        memes.clear();
//       // for (int i = 0; i < 10; i++)
//         //   memes.add("http://techdows.com/wp-content/uploads/2010/07/Opera_logo2.png");
//        //for (int i = 1; i <50 ; i=i+10) {
//          //  GoogleSerachCall("memes","d30",i);
//        //}
//        trendingBingSearch("memes");
//        recyclerAdapter = new RecyclerAdapter(memes);
//        recyclerView.setAdapter(recyclerAdapter);
//    }
//    public void GoogleSerachCall(final String KeyWordsToSearch, final String date, final Integer page ){
//        final List<String> memesUrl = new ArrayList<>();
//        Constants.whichCall(Constants.google);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                RemoteDataSource.GoogleResponse(KeyWordsToSearch,date,page)
//                        .enqueue(new Callback<GoogleResponse>() {
//                            @Override
//                            public void onResponse(Call<GoogleResponse> call, Response<GoogleResponse> response) {
//                                if (response.body() != null) {
//                                result = response.body().getItems();
//                                    Log.d(TAG, "onResponse: " + result.get(0));
//                                    for (int i = 0; i < result.size(); i++) {
//                                        Log.d(TAG, "onResponse: " + result.size());
//                                        if (result.get(i).getPagemap() != null) {
//                                            if (result.get(i).getPagemap().getCseImage() != null) {
//                                                Timber.d("onResponse: " + i + " PageMap "
//                                                        + result.get(i).getPagemap().getCseImage().get(0).getSrc());
//                                                Timber.d("onResponse: " + i + " CseImage Size "
//                                                        + result.get(i).getPagemap().getCseImage().size());
//                                                memesUrl.add(result.get(i).getPagemap().getCseImage().get(0).getSrc());
//                                            } else {
//                                                Log.d(TAG, "onResponse: PageMap is empty");
//                                            }
//                                        }
//                                    }
//                                    Message message = mhandler.obtainMessage();
//                                    message.what = 1;
//                                    message.obj = memesUrl;
//                                    mhandler.sendMessage(message);
//                                }else
//                                    Log.d(TAG, "onResponse: response.bosy() is empty ");
//                            }
//                            @Override
//                            public void onFailure(Call<GoogleResponse> call, Throwable t) {
//                                Log.d(TAG, "onFailure: "+ t.getMessage());
//                            }
//                        });
//            }
//        }).start();
//    }
//    public void searchForMemes(View view) {
//        etSerach = findViewById(R.id.etSearch);
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(etSerach.getWindowToken(), 0);
//        KeyWordrestCall(etSerach.getText().toString());
//    }
//    public void KeyWordrestCall (final String phrase){
//        Constants.whichCall(Constants.keyword);
//            RemoteDataSource.KeyWordResponse(phrase)
//                    .enqueue(new Callback<Keywords>() {
//                        @Override
//                        public void onResponse(Call<Keywords> call, Response<Keywords> response) {
//                            for (int i = 0; i < response.body().getText().size(); i++) {
//                                Log.d(TAG, "onResponse: this is i: " + i);
//                                for (int j = 0; j < response.body().getText().get(i).size(); j++) {
//                                    Log.d(TAG, "onResponse: this is j: " + j);
//                                    for (int k = 0; k < response.body().getText().get(i).get(j).size(); k++) {
//                                        Log.d(TAG, "onResponse: this is k: " + k);
//                                        Log.d(TAG, "onResponse: word "
//                                                + response.body().getText().get(i).get(j).get(k).getWord() +
//                                                " tag " + response.body().getText().get(i).get(j).get(k).getTag());
//                                        Toast.makeText(MainActivity.this, " word: " +
//                                                        response.body().getText().get(i).get(j).get(k).getWord()
//                                                        + "\ntag: " + response.body().getText().get(i).get(j).get(k).getTag()
//                                                , Toast.LENGTH_LONG).show();
//                                    }
//                                }
//                            }
//                        }
//                        @Override
//                        public void onFailure(Call<Keywords> call, Throwable t) {
//                        }
//                    });
//    }
//    public void BingSerach(final String search){
//        Constants.whichCall(Constants.bing);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final List<String> memesurl = new ArrayList<>();
//                RemoteDataSource.BingResponse(search)
//                        .enqueue(new Callback<BingSearch>() {
//                            @Override
//                            public void onResponse(Call<BingSearch> call, Response<BingSearch> response) {
//                                if (response.body() != null){
//
//                                    List<Value> item = new ArrayList<>();
//                                    if (response.body().getValue() != null){
//                                        item = response.body().getValue();
//                                        Log.d(TAG, "onResponse: Response size is " + item.size());
//                                        for (int i = 0; i <item.size() ; i++) {
//                                           memesurl.add(item.get(i).getThumbnailUrl());
//                                        }
//                                        Message message = mhandler.obtainMessage();
//                                        message.what = 1;
//                                        message.obj = memesurl;
//                                        mhandler.sendMessage(message);
//                                    }
//                                }
//                                else{
//                                    Log.d(TAG, "onResponse: bing is empty calling the google api");
//                                    GoogleSerachCall(search,null,null);
//                                }
//                            }
//                            @Override
//                            public void onFailure(Call<BingSearch> call, Throwable t) {
//
//    public void newActivity(View view) {
//        Intent intent = new Intent(this, MemeHomeActivity.class);
//        startActivity(intent);
//    }
//}
//                            }
//                        });
//            }
//        }).start();
//    }
//    public void trendingBingSearch (final String search){
//        Constants.whichCall(Constants.trending);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final List<String> memesurl = new ArrayList<>();
//                RemoteDataSource.BingTrendingResponse(search)
//                        .enqueue(new Callback<BingSearch>() {
//                            @Override
//                            public void onResponse(Call<BingSearch> call, Response<BingSearch> response) {
//                                if (response.body() != null){
//
//                                    List<Value> item = new ArrayList<>();
//                                    if (response.body().getValue() != null){
//                                        item = response.body().getValue();
//                                        Log.d(TAG, "onResponse: Trending Response size is " + item.size());
//                                        for (int i = 0; i <item.size() ; i++) {
//                                            memesurl.add(item.get(i).getThumbnailUrl());
//                                        }
//                                        Message message = mhandler.obtainMessage();
//                                        message.what = 1;
//                                        message.obj = memesurl;
//                                        mhandler.sendMessage(message);
//                                    }
//                                }
//                                else{
//                                    Log.d(TAG, "onResponse:  Trending bing is empty calling the google api");
//                                    GoogleSerachCall(search +"trending",null,null);
//                                }
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<BingSearch> call, Throwable t) {
//
//                            }
//                        });
//            }
//        }).start();
//    }
//
        memes.clear();
        /*todo rxjava causinhg an error
        RemoteDataSource.googleresult("TrendingMemes")
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GoogleResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(List<GoogleResponse> googleResponses) {
                        Log.d(TAG, "onNext: Im here here");
                        memes.add(googleResponses.get(0).getItems().get(0).getPagemap().getCseImage().get(0).getSrc());
                        Log.d(TAG, "onNext: The link is "+ googleResponses.get(0).getItems().get(0).getPagemap().getCseImage().get(0).getSrc());
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+ e.getMessage().toString());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
                */
        // for (int i = 0; i < 10; i++)
        //   memes.add("http://icons.iconarchive.com/icons/graphicloads/100-flat/256/home-icon.png");
        //for (int i = 1; i <50 ; i=i+10) {
        //  GoogleSerachCall("popular memes",null,i);
        //}
        BingSerach("popular memes");
        //recyclerAdapter = new RecyclerAdapter(memes);
    }
        private void loadTopTrending()
        {
            memes.clear();
            // for (int i = 0; i < 10; i++)
            //   memes.add("http://techdows.com/wp-content/uploads/2010/07/Opera_logo2.png");
            // recyclerAdapter = new RecyclerAdapter(memes);
            // recyclerView.setAdapter(recyclerAdapter);
            //for (int i = 1; i <50 ; i=i+10) {
            //  GoogleSerachCall("memes","d30",i);
            //}
            //trendingBingSearch("memes");
            BingSerach("memes");
            presenter.getBingSearch("memes");
        }
/*

    private void displayRecycleView() {
        Log.d(TAG, "displayRecycleView: memes list Size " +memes.size());
        for (int i = 0; i <memes.size() ; i++) {
            Log.d(TAG, "displayRecycleView: "+ i+ " meme Url " + memes.get(i));

        }
        recyclerAdapter = new RecyclerAdapter(memes);
        recyclerView.setAdapter(recyclerAdapter);
    } */

    public void GoogleSerachCall(final String KeyWordsToSearch, final String date, final Integer page ){
        final List<String> memesUrl = new ArrayList<>();
        Constants.whichCall(Constants.google);
        new Thread(new Runnable() {
            @Override
            public void run() {
                RemoteDataSource.GoogleResponse(KeyWordsToSearch,date,page)
                        .enqueue(new Callback<GoogleResponse>() {
                            @Override
                            public void onResponse(Call<GoogleResponse> call, Response<GoogleResponse> response) {
                                if (response.body() != null) {
                                result = response.body().getItems();
                                    Log.d(TAG, "onResponse: " + result.get(0));
                                    for (int i = 0; i < result.size(); i++) {
                                        Log.d(TAG, "onResponse: " + result.size());
                                        if (result.get(i).getPagemap() != null) {
                                            if (result.get(i).getPagemap().getCseImage() != null) {
                                                Timber.d("onResponse: " + i + " PageMap "
                                                        + result.get(i).getPagemap().getCseImage().get(0).getSrc());
                                                Timber.d("onResponse: " + i + " CseImage Size "
                                                        + result.get(i).getPagemap().getCseImage().size());
                                                memesUrl.add(result.get(i).getPagemap().getCseImage().get(0).getSrc());
                                            } else {
                                                Log.d(TAG, "onResponse: PageMap is empty");
                                            }
                                        }
                                    }
                                    Message message = mhandler.obtainMessage();
                                    message.what = 1;
                                    message.obj = memesUrl;
                                    mhandler.sendMessage(message);

                                }else
                                    Log.d(TAG, "onResponse: response.bosy() is empty ");
                            }
                            @Override
                            public void onFailure(Call<GoogleResponse> call, Throwable t) {
                                Log.d(TAG, "onFailure: "+ t.getMessage());
                            }
                        });
            }
        }).start();
    }
    public void searchForMemes(View view) {
        etSerach = findViewById(R.id.etSearch);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etSerach.getWindowToken(), 0);
        KeyWordrestCall(etSerach.getText().toString());
    }
    public void KeyWordrestCall (final String phrase){
        Constants.whichCall(Constants.keyword);
            RemoteDataSource.KeyWordResponse(phrase)
                    .enqueue(new Callback<Keywords>() {
                        @Override
                        public void onResponse(Call<Keywords> call, Response<Keywords> response) {
                            if (response.body() != null) {
                                String tag = "";
                                for (int i = 0; i < response.body().getText().size(); i++) {
                                    Log.d(TAG, "onResponse: this is i: " + i);
                                    for (int j = 0; j < response.body().getText().get(i).size(); j++) {
                                        Log.d(TAG, "onResponse: this is j: " + j);
                                        for (int k = 0; k < response.body().getText().get(i).get(j).size(); k++) {
                                            Log.d(TAG, "onResponse: this is k: " + k);
                                            Log.d(TAG, "onResponse: word "
                                                    + response.body().getText().get(i).get(j).get(k).getWord() +
                                                    " tag " + response.body().getText().get(i).get(j).get(k).getTag());
                                           Toast.makeText(MainActivity.this, " word: " +
                                                           response.body().getText().get(i).get(j).get(k).getWord()
                                                            + "\ntag: " + response.body().getText().get(i).get(j).get(k).getTag()
                                                    , Toast.LENGTH_LONG).show();
                                            tag = response.body().getText().get(i).get(j).get(k).getTag();
                                            Log.d(TAG, "onResponse: get tag " + tag );
                                        }
                                    }
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<Keywords> call, Throwable t) {
                        }
                    });
    }
    public void BingSerach(final String search)
    {
        Constants.whichCall(Constants.bing);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                final List<String> memesurl = new ArrayList<>();
                RemoteDataSource.BingResponse(search)
                        .enqueue(new Callback<BingSearch>() {
                            @Override
                            public void onResponse(Call<BingSearch> call, Response<BingSearch> response) {
                                if (response.body() != null){

                                    List<Value> item = new ArrayList<>();
                                    if (response.body().getValue() != null){
                                        item = response.body().getValue();
                                        Log.d(TAG, "onResponse: Bing Response size is " + item.size());
                                        for (int i = 0; i <item.size() ; i++) {
                                            memesurl.add(item.get(i).getThumbnailUrl());
                                        }
                                        Message message = mhandler.obtainMessage();
                                        message.what = 1;
                                        message.obj = memesurl;
                                        mhandler.sendMessage(message);


                                    }
                                }

                            }
                            

                            @Override
                            public void onFailure(Call<BingSearch> call, Throwable t) {
                                Log.d(TAG, "onResponse:  bing is empty calling the google api");
                                GoogleSerachCall(search ,null,null);

//    public void newActivity(View view) {
//    }
}
                            });
                            }
        }).start();

    }

    public void trendingBingSearch (final String search){
        Constants.whichCall(Constants.trending);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<String> memesurl = new ArrayList<>();
                RemoteDataSource.BingTrendingResponse(search)
                        .enqueue(new Callback<BingSearch>() {
                            @Override
                            public void onResponse(Call<BingSearch> call, Response<BingSearch> response) {
                                if (response.body() != null){

                                    List<Value> item = new ArrayList<>();
                                    if (response.body().getValue() != null){
                                        item = response.body().getValue();
                                        Log.d(TAG, "onResponse: Trending Response size is " + item.size());
                                        for (int i = 0; i <item.size() ; i++) {
                                            memesurl.add(item.get(i).getThumbnailUrl());
                                        }
                                        Message message = mhandler.obtainMessage();
                                        message.what = 1;
                                        message.obj = memesurl;
                                        mhandler.sendMessage(message);
                                    }
                                }
                                else{
                                    Log.d(TAG, "onResponse:  Trending bing is empty calling the google api");
                                    GoogleSerachCall(search +"trending",null,null);
                                }

                            }

                            @Override
                            public void onFailure(Call<BingSearch> call, Throwable t) {

                            }
                        });
            }
        }).start();
    }

    public void newActivity(View view) {
    }

    //This function will run on successful Facebook login
    @Override
    public void onSuccess() {
        //TODO Do something after the user successfully logs into Facebook!
    }
}
