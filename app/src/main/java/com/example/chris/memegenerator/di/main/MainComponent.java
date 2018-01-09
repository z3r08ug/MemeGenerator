package com.example.chris.memegenerator.di.main;

import com.example.chris.memegenerator.view.createMeme.CreateMemeActivity;
import com.example.chris.memegenerator.view.main.MainActivity;
import com.example.chris.memegenerator.view.main.MemeHomeActivity;

import java.lang.reflect.Member;

import dagger.Subcomponent;

/**
 * Created by Admin on 11/29/2017.
 */

@Subcomponent(modules = MainModule.class)
public interface MainComponent
{
    void inject(MemeHomeActivity memeHomeActivity);
    void inject(CreateMemeActivity createMemeActivity);
}
