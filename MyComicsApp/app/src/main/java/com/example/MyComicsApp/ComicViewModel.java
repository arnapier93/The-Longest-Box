package com.example.MyComicsApp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ComicViewModel extends AndroidViewModel {
    private ComicsRepository comicsRepository;
    private final LiveData<List<Comic>> listLiveData;
    public ComicViewModel(Application application){
        super(application);
        comicsRepository = new ComicsRepository(application);
        listLiveData = comicsRepository.getAllComics();
    }
    public LiveData<List<Comic>> getAllComicsFromVM(){
        return listLiveData;
    }
    public void insertComic(Comic comic){
        comicsRepository.insertComic(comic);
    }

    public void delete() {
        comicsRepository.delete();
    }
}