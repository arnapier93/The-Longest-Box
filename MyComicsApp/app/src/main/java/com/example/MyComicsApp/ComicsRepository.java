package com.example.MyComicsApp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ComicsRepository {
    ComicLibraryDatabase comicLibraryDatabase;
    ComicDao comicDao;
    private LiveData<List<Comic>> listComics;

    public ComicsRepository(Application application){
        comicLibraryDatabase = ComicLibraryDatabase.getDatabase(application);
        comicDao = comicLibraryDatabase.comicDao();
        listComics = comicDao.getComics();
    }

    public void insertComic(Comic comic){
        ComicLibraryDatabase.databaseWriteExecutor.execute(() ->
                comicDao.insert(comic));
    }

    public LiveData<List<Comic>> getAllComics() {
        return listComics;
    }

    public void delete() {
        ComicLibraryDatabase.databaseWriteExecutor.execute(() ->
                comicDao.delete());
    }
}
