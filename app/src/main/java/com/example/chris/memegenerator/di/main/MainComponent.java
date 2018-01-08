package com.example.chris.memegenerator.di.main;

import com.example.chris.memegenerator.view.main.MainActivity;
import com.example.chris.memegenerator.view.main.MemeHomeActivity;

import dagger.Subcomponent;

/**
 * Created by Admin on 11/29/2017.
 */

@Subcomponent(modules = MainModule.class)
public interface MainComponent
{
    void inject(MainActivity mainActivity);
}
