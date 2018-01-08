package com.example.chris.memegenerator.view.createMeme;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.util.FacebookHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateMemeActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback
{
    public static final int PICK_PHOTO_FOR_MEME = 8;
    private static final String TAG = CreateMemeActivity.class.getSimpleName() + "_TAG";
    private ImageView ivMeme;
    private EditText etBottom;
    private EditText etTop;
    private Bitmap meme;
    private FacebookHandler facebookHandler;
    private Bitmap combined;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meme);
    }
    
    private void bindViews()
    {
        ivMeme = findViewById(R.id.ivCreateMeme);
        etTop = findViewById(R.id.etTop);
        etBottom = findViewById(R.id.etBottom);
        
        facebookHandler = FacebookHandler.getInstance();
    }
    
    public void pickImage()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_MEME);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != RESULT_OK)
        {
            return;
        }
        if (requestCode == PICK_PHOTO_FOR_MEME)
        {
//            final Bundle extras = data.getExtras();
//            if (extras != null)
//            {
//                //Get image
//                meme = extras.getParcelable("data");
//                meme = getResizedBitmap(meme, 1080, 1080);
//                Log.d(TAG, "onActivityResult: width" + meme.getWidth());
//                Log.d(TAG, "onActivityResult: height"+ meme.getHeight());
//
//                ivMeme.setImageBitmap(meme);
//
//
//            }
            
            Uri uri = data.getData();
            
            try
            {
                meme = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ivMeme.setImageBitmap(meme);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return resizedBitmap;
    }
    
    private void createMeme(Bitmap meme)
    {
        etTop.setDrawingCacheEnabled(true);
        etBottom.setDrawingCacheEnabled(true);
        
        Bitmap bmp = Bitmap.createBitmap(etTop.getDrawingCache());
        
        combined = combineImages(meme,bmp, true);
        etTop.setText("");
        bmp = Bitmap.createBitmap(etBottom.getDrawingCache());
        combined = combineImages(combined, bmp, false);
        ivMeme.setImageBitmap(combined);
        ivMeme.setMaxWidth(600);
        ivMeme.setMaxHeight(600);
        etTop.setVisibility(View.INVISIBLE);
        etBottom.setVisibility(View.INVISIBLE);
    }
    
    public Bitmap combineImages(Bitmap background, Bitmap foreground, boolean top)
    {
        
        int width = 0, height = 0;
        Bitmap cs;
        
        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();
        
        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(cs);
        background = Bitmap.createScaledBitmap(background, width, height, true);
        comboImage.drawBitmap(background, 0, 0, null);
        if (top)
            comboImage.drawBitmap(foreground, 0,400, null);
        else
            comboImage.drawBitmap(foreground, 0, 1500, null);
        
        return cs;
    }
    
    public void onCreateMeme(View view)
    {
        createMeme(meme);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Created Meme");
        builder.setMessage("Which action would you like to perform?");
        builder.setPositiveButton("Share", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                facebookHandler.shareDialog(combined, CreateMemeActivity.this);
            }
        });
        builder.setNegativeButton("Save to Device", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                isStoragePermissionGranted();
            }
        });
        builder.show();
    }
    
    private void saveMeme()
    {
//        File root = Environment.getExternalStorageDirectory();
//        File file = new File(root.getAbsolutePath()+"/DCIM/Camera/meme.jpg");
//        try
//        {
//            file.createNewFile();
//            FileOutputStream ostream = new FileOutputStream(file);
//            combined.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
//            ostream.close();
//            Toast.makeText(this, "Saved Meme", Toast.LENGTH_SHORT).show();
//        }
//        catch (Exception e)
//        {
//            Log.d(TAG, "saveMeme: "+e.toString());
//            Toast.makeText(this, "Error Saving Meme", Toast.LENGTH_SHORT).show();
//        }
    
    
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+ "/Camera/Your_Directory_Name";
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Meme.png";
        File file = new File(myDir, fname);
        System.out.println(file.getAbsolutePath());
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            combined.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(this, "Saved Meme", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to Save Meme", Toast.LENGTH_SHORT).show();
        }
    
        MediaScannerConnection.scanFile(this, new String[]{file.getPath()}, new String[]{"image/jpeg"}, null);
    }
    
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                saveMeme();
                return true;
            } else {
                
                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            saveMeme();
            return true;
        }
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
            saveMeme();
        }
    }
    
    public void searchForPicture(View view)
    {
        //search for pic
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateMemeActivity.this);
        alertDialog.setTitle("Search For Meme");
        alertDialog.setMessage("Meme Keywords:");
        
        final EditText input = new EditText(CreateMemeActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        
        alertDialog.setPositiveButton("Search",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String keyword = input.getText().toString();
                        
                        //perform search
                    }
                });
        
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        
        alertDialog.show();
    }
    
    public void chooseFromGallery(View view)
    {
        setContentView(R.layout.activity_create_meme);
        bindViews();
        
        pickImage();
    }
}
