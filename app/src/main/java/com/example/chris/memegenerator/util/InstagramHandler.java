package com.example.chris.memegenerator.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by Nabeel on 1/7/2018.
 */

public class InstagramHandler {

    public static void createInstagramIntent(String type, String mediaPath, Context context) {

        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType(type);

        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = Uri.fromFile(media);

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);

        // Broadcast the Intent.
        context.getApplicationContext().startActivity(Intent.createChooser(share,"Share To"));
    }

}
