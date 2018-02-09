package com.example.chris.memegenerator.di.createMeme;

import com.example.chris.memegenerator.view.createMeme.CreateMemeActivity;

import dagger.Subcomponent;

/**
 * Created by Admin on 11/29/2017.
 */

@Subcomponent(modules = CreateMemeModule.class)
public interface CreateMemeComponent
{
    void inject(CreateMemeActivity createMemeActivity);
}
