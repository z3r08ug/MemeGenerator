package com.example.chris.memegenerator.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.FaceDetector;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.fragments.memesliderfrag.MemeSliderFragment;
import com.example.chris.memegenerator.services.DownloadService;
import com.example.chris.memegenerator.view.createMeme.CreateMemeActivity;
import com.example.chris.memegenerator.view.main.MemeHomeActivity;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Admin on 1/2/2018.
 */

public class MemeSliderAdapter extends PagerAdapter {
  //  int[] imageIds ={R.drawable.meme1,R.drawable.meme2,R.drawable.meme3,R.drawable.meme4,R.drawable.meme5};
    List<Image> imageList=new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;
int setCurrentImage;
    public MemeSliderAdapter(Context context, List<Image> images, int imageUrl) {
        this.context = context;
        this.imageList= images;
        this.setCurrentImage= imageUrl;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.memeslider, container, false);
        ImageView memeSlider=view.findViewById(R.id.ivMemeSlider);
        Button shareOnFacebook = view.findViewById(R.id.shareOnFacebook);
        final String imageUrl = imageList.get(position).getImageUrl();
        Log.d("Check", "instantiateItem: "+imageUrl);
       // memeSlider.setImageResource(imageList.get(position).getImageUrl());

        Glide.with(context).load(imageUrl).into(memeSlider);
        final ImageButton btnFavMeme= view.findViewById(R.id.btnFavMeme);
        btnFavMeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.button_animation);
                btnFavMeme.startAnimation(myAnim);
            }
        });
shareOnFacebook.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {

        Handler handler = new Handler(new Handler.Callback() {
            public static final String TAG="Thisis";
            @Override
            public boolean handleMessage(Message message) {

                Log.d(TAG, "handleMessage: "+message);
                Log.d(TAG, "handleMessage: "+message.getData().getParcelable("bitmap"));
                Log.d(TAG, "handleMessage: "+(message.getData().getParcelable("bitmap")==null));
                Log.d(TAG, "handleMessage: "+(message.getData().getParcelable("bitmap") instanceof Bitmap));

                Bitmap savedBitmap = message.getData().getParcelable("bitmap");
                FacebookHandler facebookHandler = FacebookHandler.getInstance();
               facebookHandler.shareDialog(savedBitmap, (MemeHomeActivity) context);
//
                return true;
            }
        });
        DownloadService downloadService = new DownloadService(handler, imageUrl);
        downloadService.start();
    }
});
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((ScrollView)object);
    }


}
