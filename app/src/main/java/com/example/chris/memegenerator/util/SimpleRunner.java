package com.example.chris.memegenerator.util;


import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This is just a class I'm going to use to test out generic stuff, run simple things, not considered part of the smartMeme main source code.
 */
public class SimpleRunner {
    public static final String TAG = "SimpleRunner";
    private static List<String> celebrities = new ArrayList<String>();

    public static void main(String[] args)
    {
        String filePath = "/Users/robertzingarelli/Git_meme/app/src/main/java/com/example/chris/memegenerator/util/celebrities.txt";

       // readCelebrityNames( filePath );
        //Log.d(TAG, "main: first element...." + celebrities.get(0));
       // System.out.println("our first element " + celebrities.get(0));
    }


    public static List<String> readCelebrityNames()
    {
        String filePath = "/Users/robertzingarelli/Git_meme/app/src/main/java/com/example/chris/memegenerator/util/celebrities.txt";
        StringBuilder contentBuilder = new StringBuilder();
        try {
              BufferedReader  br = new BufferedReader(new FileReader(filePath));


            String sCurrentLine;
            String[] parts = new String[2];
            int counter = 1;
            while ((sCurrentLine = br.readLine()) != null)
            {
                parts = sCurrentLine.split(",");
                celebrities.add(parts[1]);
                counter++;
                if ((counter % 50) == 0)
                {
                   // Log.d(TAG, "readCelebrityNames: we just added the name " + parts[1]);
                }
                //contentBuilder.append(sCurrentLine).append("\n");
            }
           // Log.d(TAG, "readCelebrityNames: at the end our list of celebs size is " + celebrities.size());
        }
        catch (Exception e)
        {
            Log.d(TAG, "readCelebrityNames: got an exception here...");
            e.printStackTrace();
        }
        return celebrities;
    }

}
