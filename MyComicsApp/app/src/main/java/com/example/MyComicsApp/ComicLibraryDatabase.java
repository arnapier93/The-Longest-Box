package com.example.MyComicsApp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Comic.class}, version = 1  , exportSchema = false)
public abstract class ComicLibraryDatabase extends RoomDatabase {
    public abstract  ComicDao comicDao();
    private static volatile ComicLibraryDatabase comicLibraryDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ComicLibraryDatabase getDatabase(final Context context){
        if(comicLibraryDatabase == null){
            synchronized (ComicLibraryDatabase.class){
                if(comicLibraryDatabase == null){
                    comicLibraryDatabase =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    ComicLibraryDatabase.class,
                                    "comics_library").build();
                }
            }
        }
        return comicLibraryDatabase;
    }
}
