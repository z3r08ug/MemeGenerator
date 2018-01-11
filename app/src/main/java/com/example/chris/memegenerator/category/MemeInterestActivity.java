package com.example.chris.memegenerator.category;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.view.main.MemeHomeActivity;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MemeInterestActivity extends AppCompatActivity
{
    
    private static final String TAG = MemeInterestActivity.class.getSimpleName() + "_TAG";
    private RecyclerView recyclerView;
    private List<MemesCategory> memesCategoryList;
    private FileOutputStream fos;
    private CategoryRCAdapter categoryRCAdapter;
    RecyclerView rcSearchImages;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_catergory_selec);



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
    
        recyclerView = findViewById(R.id.rcCategoryView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        memesCategoryList = new ArrayList<>();
        memesCategoryList.add(new MemesCategory("Angry", false));
        memesCategoryList.add(new MemesCategory("Politics", false));
        memesCategoryList.add(new MemesCategory("Animals", false));
        memesCategoryList.add(new MemesCategory("Reaction", false));
        memesCategoryList.add(new MemesCategory("Feelings", false));
        memesCategoryList.add(new MemesCategory("Romance", false));
        categoryRCAdapter = new CategoryRCAdapter(memesCategoryList);
        recyclerView.setAdapter(categoryRCAdapter);
    }

    public void homeActivity(View view)
    {
        List<String> interests = categoryRCAdapter.getInterests();
        try
        {
            fos = openFileOutput("interests.txt", Context.MODE_PRIVATE);
            for (String interest : interests)
            {
                interest += " memes,";
                fos.write(interest.getBytes());
            }
            fos.close();
            Toast.makeText(this, "Saved interests", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        startMemeHomeActivity();
    }
    
    private void startMemeHomeActivity()
    {
        Intent intent = new Intent(this, MemeHomeActivity.class);
        startActivity(intent);
        this.finish();
    }


}

