package com.example.chris.memegenerator.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by Nabeel on 12/11/2017.
 * This is a singleton class designed to handle facebook interaction
 *
 */

public class FacebookHandler {

    private static final String TAG = "FacebookHandlerTag";
    private AccessToken accessToken=null;
    private CallbackManager callbackManager;
    private static FacebookHandler handler=null;

    private FacebookHandler() {

    }

    public static synchronized FacebookHandler getInstance() {
        if(handler==null)
            handler = new FacebookHandler();
        return handler;
    }

    //Call in onCreate stage to register facebook login button
    public void registerLoginButton(LoginButton fbLoginButton) {
        callbackManager = CallbackManager.Factory.create();
        //Ask for email and user_likes permissions
        //We will ask for publish to wall permission later on when user is ready to post
        fbLoginButton.setReadPermissions("email","user_likes");
        fbLoginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess: "+loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "onCancel: ");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG, "facebook:nError: ",exception);
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken: "+token);
        accessToken = token;
    }

    public AccessToken getAccessToken() {
        //If user is already logged in, set accessToken to current one
        if(accessToken==null)
            if(AccessToken.getCurrentAccessToken()!=null)
                accessToken=AccessToken.getCurrentAccessToken();

        return accessToken;
    }

    public void getPublishPermission(Activity activity) {
        LoginManager.getInstance().logInWithPublishPermissions(activity, Arrays.asList("publish_actions"));
    }

    public Set<String> getCurrentPermissions() {
        return accessToken.getCurrentAccessToken().getPermissions();
    }

    public Set<String> getDeclinedPermissions() {
        return accessToken.getCurrentAccessToken().getDeclinedPermissions();
    }

    //Make Post to Facebook
    public void shareDialog(Bitmap bitmap, Activity activity) {
        SharePhoto sharePhoto = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();
        ShareContent shareContent = new ShareMediaContent.Builder()
                .addMedium(sharePhoto)
                .build();
        ShareDialog shareDialog = new ShareDialog(activity);
        shareDialog.show(shareContent, ShareDialog.Mode.AUTOMATIC);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}