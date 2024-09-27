package com.example.MyComicsApp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Comic comic);
    @Update
    void update(Comic comic);
    @Query("SELECT * from comics_library ORDER by seriesTitle Asc")
    LiveData<List<Comic>> getComics();
    @Query("DELETE FROM comics_library")
    void delete();
}