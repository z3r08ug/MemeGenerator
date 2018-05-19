package com.example.chris.memegenerator.view.home;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.chris.memegenerator.util.SimpleRunner;
import com.example.chris.memegenerator.util.SyntaxBuilder;
import com.example.chris.memegenerator.view.login.LoginActivity;
import com.example.chris.memegenerator.MemeApplication;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.category.MemeInterestActivity;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.fragments.interestfragment.TrendingInterestFragment;
import com.example.chris.memegenerator.fragments.toptrendingfragment.TrendingFragment;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.handlers.FacebookHandler;
import com.example.chris.memegenerator.util.adapters.MainPagerViewAdapter;
import com.example.chris.memegenerator.util.ZoomOutPageTransformer;
import com.example.chris.memegenerator.model.pojo.keywordfinder.Keywords;
import com.example.chris.memegenerator.view.favorites.FavoriteMemesActivity;
import com.example.chris.memegenerator.view.smartsearch.SmartSearchActivity;
import com.example.chris.memegenerator.view.createMeme.CreateMemeActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemeHomeActivity extends AppCompatActivity implements MemeHomeContract.View{
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private static final String TAG = MemeHomeActivity.class.getSimpleName() + "_TAG";
    @Inject
    MemeHomePresenter presenter;
    MainPagerViewAdapter mainViewPagerAdapter;
    TabLayout mainTabLayout;
    ViewPager viewPager;
    private Toolbar homeToolbar;
    FrameLayout frameLayout;
    List<String> interests;
    EditText etpost;
    List<List<String>> interestsMemes;
    List<String> keywordList;
    List<String> celebrities = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_meme_activity);
        MemeApplication.get(this).getMemeHomeComponent().inject(this);
        
        homeToolbar = findViewById(R.id.home_toolbar);
        homeToolbar.setBackgroundColor(Color.parseColor("#19B5FE"));
        
        viewPager = findViewById(R.id.app_main_pager);
        mainTabLayout = findViewById(R.id.app_main_tabs);
        etpost = findViewById(R.id.etpost);
        mainTabLayout.setBackgroundColor(Color.parseColor("#1E8BC3"));
        mainViewPagerAdapter = new MainPagerViewAdapter(getSupportFragmentManager());
        presenter.attachView(this);
        Constants.setallFALSE();
        Constants.whichCall(Constants.bing);
        
        presenter.getBingResponseTrending("trending memes");
        viewPager.setAdapter(mainViewPagerAdapter);
        
        mainTabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(homeToolbar);
        mainViewPagerAdapter.notifyDataSetChanged();
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        
        frameLayout = findViewById(R.id.searchFragmentFrame);
        interests = new ArrayList<>();
        interestsMemes = new ArrayList<>();
        keywordList = new ArrayList<>();
        
        try
        {
            InputStream instream = openFileInput("interests.txt");
            if (instream != null)
            {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line,line1 = "";
                try
                {
                    while ((line = buffreader.readLine()) != null)
                        line1+=line;
                    
                    presenter.getBingResponseInterests(line1);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            Log.d(TAG, "onCreate: "+e.toString());
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId())
        {
            case R.id.itemFavorites:
                if (frameLayout.getVisibility() == View.VISIBLE)
                    onBackPressed();
                Intent intentFav = new Intent(this, FavoriteMemesActivity.class);
                startActivity(intentFav);
                break;
            case R.id.itemSettings:
                if (frameLayout.getVisibility() == View.VISIBLE)
                    onBackPressed();
                Intent intentSearch = new Intent(this, MemeInterestActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.itemCreate:
                if (frameLayout.getVisibility() == View.VISIBLE)
                    onBackPressed();
                Intent intentCreate = new Intent(this, CreateMemeActivity.class);
                startActivity(intentCreate);
                break;
            case R.id.itemLogOut:
                if (frameLayout.getVisibility() == View.VISIBLE)
                    onBackPressed();
                FacebookHandler facebookHandler = FacebookHandler.getInstance();
                facebookHandler.logout();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
        
    }
    
    @Override
    public void showError(String error)
    {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }
    
    
    public void setKeywords(Keywords keywords)
    {
        SyntaxBuilder sb = new SyntaxBuilder();
        if (keywords != null)
        {
            String tag = "";
            String word = "";

            celebrities = SimpleRunner.readCelebrityNames();
            for (int i = 0; i < keywords.getText().size(); i++)
            {
                // Log.d(TAG, "onResponse: this is i: " + i);
                for (int j = 0; j < keywords.getText().get(i).size(); j++)
                {
                    // Log.d(TAG, "onResponse: this is j: " + j);
                    for (int k = 0; k < keywords.getText().get(i).get(j).size(); k++)
                    {
                        // Log.d(TAG, "onResponse: this is k: " + k);
                        tag = keywords.getText().get(i).get(j).get(k).getTag();
                        if (tag.equals("VERB"))
                        {
                            word = keywords.getText().get(i).get(j).get(k).getWord();
                            sb.addVerb(word);
                        }
                        if (tag.equals("NOUN")  )
                        {
                            Log.d("MYOWNTAG", "onResponse: word "+
                                    keywords.getText().get(i).get(j).get(k).getWord()
                                    + "\ntag is: " + tag);
                        
                            if (tag.equals("NOUN") && word != null && word.length() >0 && Character.isUpperCase(word.charAt(0))
                                    && Character.isUpperCase(keywords.getText().get(i).get(j).get(k).getWord().charAt(0)))
                            {
                                String proper_name = word + " " + keywords.getText().get(i).get(j).get(k).getWord();
                                keywordList.remove(word);
                                keywordList.add(proper_name);
                                Log.d(TAG, "onResponse: saving the proper_name "
                                        + proper_name +
                                        " tag: " + tag);
                                if (celebrities.contains(proper_name))
                                {
                                    Log.d(TAG, "setKeywords: we've got a celebrity name...");
                                    sb.addCelebrity(word);
                                }
                                else
                                {
                                    sb.addName(word);  //if it's a proper name, and not a celebrity, than it's just a regular person
                                }

                            }
                            else
                            {
                                word = keywords.getText().get(i).get(j).get(k).getWord();
                                keywordList.add(word);
                                sb.addNoun(word);
                                Log.d("keywords", "onResponse: saving the word "
                                        + word +
                                        " tag: " + tag);
                            }
                        
                        }
                    }
                }
            }
        }
        
        goToSmartSearchActivity(sb);
    }
    
    @Override
    public void setBingResponseTrending(List<String> memes)
    {
        TrendingFragment.newInstance(memes);
    }
    
    @Override
    public void setBingResponseInterests(List<String> memes)
    {
        TrendingInterestFragment.newInstance(memes);
    }
    
    public void goToSmartSearchActivity(SyntaxBuilder sb)
    {
        
        Intent intent = new Intent(this, SmartSearchActivity.class);
        intent.putStringArrayListExtra("magic", (ArrayList<String>) keywordList);
        intent.putExtra("SyntaxBuilder", sb); //SyntaxBuilder is the object we want now.  I'll keep the 'magic' arraylist in there for now
        if (frameLayout.getVisibility() == View.VISIBLE)
            onBackPressed();
        startActivity(intent);
    }
    
    
    @Override
    public void showProgress(String progress)
    {
        Toast.makeText(this, progress, Toast.LENGTH_SHORT).show();
    }
    
    public void parseKeyWords(View view)
    {
        presenter.getKeywords(etpost.getText().toString());
    }
    
    public void textToSpeach(View view)
    {
        startVoiceInput();
    }
    private void startVoiceInput()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try
        {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        }
        catch (ActivityNotFoundException e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    etpost.setText(result.get(0));
                }
                break;
            }
            
        }
    }
}
