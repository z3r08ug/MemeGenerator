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
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chris.memegenerator.MemeApplication;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.services.DownloadService;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.FacebookHandler;
import com.example.chris.memegenerator.util.Image;
import com.example.chris.memegenerator.util.InstagramHandler;
import com.example.chris.memegenerator.util.RecyclerAdapter;
import com.example.chris.memegenerator.util.RecyclerAdapter2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class CreateMemeActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, CreateMemeContract.View
{
    public static final int PICK_PHOTO_FOR_MEME = 8;
    private static final String TAG = CreateMemeActivity.class.getSimpleName() + "_TAG";
    private ImageView ivMeme;
    private EditText etBottom;
    private EditText etTop;
    private Bitmap meme;
    private FacebookHandler facebookHandler;
    private Bitmap combined;
    @Inject
    CreateMemePresenter presenter;
    private RecyclerView rcSearchImages;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meme);
        MemeApplication.get(this).getCreateComponent().inject(this);
        presenter.attachView(this);
        rcSearchImages = findViewById(R.id.rcSearchImages);
        rcSearchImages.setLayoutManager(new GridLayoutManager(this,2));
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
        etBottom.setTextColor(this.getResources().getColor(R.color.whiteBYRIZ));
        etTop.setTextColor(this.getResources().getColor(R.color.whiteBYRIZ));
        
        Bitmap bmp = Bitmap.createBitmap(etTop.getDrawingCache());

        combined = combineImages(meme,bmp, true);
        etTop.setText("");
        bmp = Bitmap.createBitmap(etBottom.getDrawingCache());
        combined = combineImages(combined, bmp, false);
        ivMeme.setImageBitmap(combined);
        ivMeme.setMaxWidth(100);
        ivMeme.setMaxHeight(100);
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
            comboImage.drawBitmap(foreground, 0,300, null);
        else
            comboImage.drawBitmap(foreground, 0, 1500, null);

        return cs;
    }

    public void onCreateMeme(View view)
    {
        rizCreateMeme(meme);
    }

    private void rizCreateMeme(Bitmap bitmap) {
        createMeme(bitmap);

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
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+ "/Camera/Your_Directory_Name";
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Meme.png";
        File file = new File(myDir, fname);
        Log.d(TAG, "saveMeme: " + file.getAbsolutePath());
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            combined.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(this, "Saved Meme", Toast.LENGTH_SHORT).show();

            InstagramHandler instagramHandler = new InstagramHandler();
            instagramHandler.createInstagramIntent(file.getAbsolutePath(), "Meme", this);
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
                Log.d(TAG,"Permission is granted");
                saveMeme();
                return true;
            } else {

                Log.d(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.d(TAG,"Permission is granted");
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
            Log.d(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
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

                        presenter.getBingSearch(keyword, Constants.bing);
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

    @Override
    public void showError(String error)
    {

    }

    @Override
    public void setBingSearch(final List<String> memes)
    {
        List<Image> imageList = new ArrayList<>();
        for (String meme : memes)
        {
            Log.d(TAG, "setBingSearch: Creat MEME " + meme);
            imageList.add(new Image(meme));
        }

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(imageList, new RecyclerAdapter.onMemeClickListner() {
            Bitmap theBitmap;
            @Override
            public void onMemeClick(Image image, int position) {

          Handler handler = new Handler(new Handler.Callback() {
              @Override
              public boolean handleMessage(Message message) {
                  Log.d(TAG, "handleMessage: "+message);
                  Log.d(TAG, "handleMessage: "+message.getData().getParcelable("bitmap"));
                  Log.d(TAG, "handleMessage: "+(message.getData().getParcelable("bitmap")==null));
                  Log.d(TAG, "handleMessage: "+(message.getData().getParcelable("bitmap") instanceof Bitmap));
                  setContentView(R.layout.activity_create_meme);
                  bindViews();
                  Bitmap savedBitmap = message.getData().getParcelable("bitmap");
                 ivMeme.setImageBitmap(savedBitmap);
                  return true;
              }
          });

                DownloadService downloadService = new DownloadService(handler, image.getImageUrl());
                downloadService.start();

            }
        });
rcSearchImages.setAdapter(recyclerAdapter);

    }
    
    @Override
    public void showProgress(String progress)
    {
    
    }
}
