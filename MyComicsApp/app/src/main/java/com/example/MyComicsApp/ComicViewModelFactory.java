package com.example.MyComicsApp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ComicViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    public ComicViewModelFactory(Application myApplication){
        application = myApplication;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        return (T) new ComicViewModel(application);
    }
}
