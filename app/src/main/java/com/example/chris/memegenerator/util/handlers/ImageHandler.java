package com.example.chris.memegenerator.util.handlers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Nabeel on 1/10/2018.
 */

public class ImageHandler {

    private static final String TAG = "ImageHandlerTag";
    private static Bitmap image;
    private static String fileName;

    //Saves the bitmap image with provided file name and returns the filepath if successful, otherwise null
    public static String saveImage(Bitmap bitmap, String file_name, Activity activity) {
        image = bitmap;
        fileName = file_name;
        if(!isStoragePermissionGranted(activity))
            return null;
        else
            return saveMeme(activity);
    }

    private static boolean isStoragePermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG,"Permission is granted");
                return true;
            } else {

                Log.d(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.d(TAG,"Permission is granted");
            return true;
        }
    }

    private static String saveMeme(Activity activity) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+ "/Camera/Your_Directory_Name";
        File myDir = new File(root);
        myDir.mkdirs();
        File file = new File(myDir, fileName);
        Log.d(TAG, "saveMeme: " + file.getAbsolutePath());
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fileName);
        try {
            FileOutputStream out = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(activity, "Saved Meme", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "Failed to Save Meme", Toast.LENGTH_SHORT).show();
            return null;
        }

        MediaScannerConnection.scanFile(activity, new String[]{file.getPath()}, new String[]{"image/jpeg"}, null);

        return file.getAbsolutePath();
    }

}
