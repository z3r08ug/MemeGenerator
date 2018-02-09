package com.example.chris.memegenerator.di.memehome;

import com.example.chris.memegenerator.view.createMeme.CreateMemeActivity;
import com.example.chris.memegenerator.view.home.MemeHomeActivity;

import dagger.Subcomponent;

/**
 * Created by Admin on 11/29/2017.
 */

@Subcomponent(modules = MemeHomeModule.class)
public interface MemeHomeComponent
{
    void inject(MemeHomeActivity memeHomeActivity);
}
