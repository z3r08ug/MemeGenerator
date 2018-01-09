package com.example.chris.memegenerator.services;

import android.app.IntentService;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.chris.memegenerator.util.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nabeel on 1/9/2018.
 */

public class DownloadService extends Thread{

    String img_url;
    Handler handler;

    public DownloadService(Handler handler, String url) {
        this.handler = handler;
        this.img_url = url;
    }

    @Override
    public void run() {
        super.run();

        Bitmap myBitmap = null;

        try
        {
            URL url = new URL(img_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //Send Bitmap back to main thread through handler
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bitmap",myBitmap);
        message.setData(bundle);
        handler.sendMessage(message);
    }

}