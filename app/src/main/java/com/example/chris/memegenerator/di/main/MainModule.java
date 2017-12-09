package com.example.chris.memegenerator.di.main;


import com.example.chris.memegenerator.view.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Admin on 11/29/2017.
 */

@Module
public class MainModule
{
    @Provides
    MainPresenter providerMainPresenter()
    {
        return new MainPresenter();
    }
}
