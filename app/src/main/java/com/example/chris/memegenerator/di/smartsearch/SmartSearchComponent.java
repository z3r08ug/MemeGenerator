package com.example.chris.memegenerator.di.smartsearch;


import com.example.chris.memegenerator.view.smartsearch.SmartSearchActivity;

import dagger.Subcomponent;

/**
 * Created by Admin on 11/29/2017.
 */

@Subcomponent(modules = SmartSearchModule.class)
public interface SmartSearchComponent
{
    void inject(SmartSearchActivity smartSearchActivity);
}
