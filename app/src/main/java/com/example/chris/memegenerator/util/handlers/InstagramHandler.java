package com.example.chris.memegenerator.util.handlers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.BuildConfig;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;

/**
 * Created by Nabeel on 1/7/2018.
 */

public class InstagramHandler
{
    
    public InstagramHandler()
    {
    }
    
    public static void createInstagramIntent(String mediaPath, String title, Context context) {

        Log.d("InstagramHandlerTag", "createInstagramIntent: "+mediaPath);

        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType("image/*");

        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = FileProvider.getUriForFile(context, "com.example.chris.memegenerator.provider",media);

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.putExtra(Intent.EXTRA_TITLE, title);

        // Broadcast the Intent.
        context.getApplicationContext().startActivity(Intent.createChooser(share,"Share To"));
    }
}
