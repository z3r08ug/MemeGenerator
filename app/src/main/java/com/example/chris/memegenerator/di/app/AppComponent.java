package com.example.chris.memegenerator.di.app;

import com.example.chris.memegenerator.di.createMeme.CreateMemeComponent;
import com.example.chris.memegenerator.di.createMeme.CreateMemeModule;
import com.example.chris.memegenerator.di.memehome.MemeHomeComponent;
import com.example.chris.memegenerator.di.memehome.MemeHomeModule;
import com.example.chris.memegenerator.di.smartsearch.SmartSearchComponent;
import com.example.chris.memegenerator.di.smartsearch.SmartSearchModule;

import dagger.Component;

/**
 * Created by chris on 12/7/2017.
 */

@Component(modules = AppModule.class)
public interface AppComponent
{
    MemeHomeComponent add(MemeHomeModule memeHomeModule);
    CreateMemeComponent add(CreateMemeModule createMemeModule);
    SmartSearchComponent add(SmartSearchModule smartSearchModule);
}
