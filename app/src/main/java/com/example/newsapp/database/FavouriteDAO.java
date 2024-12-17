package com.example.newsapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.newsapp.Models.News;
import com.example.newsapp.Models.favourite;

import java.util.List;

@Dao
public interface FavouriteDAO {
    @Insert
    void insertNews(favourite favourite);
    @Query("SELECT COUNT(*) FROM favourite WHERE title = :title AND uId  = :userId")
    int checkIfArticleExists(String title, String userId);

    @Query("SELECT * FROM favourite WHERE uId = :userId")
    List<favourite> getAllNewsForUser(String userId);

    @Query("DELETE FROM favourite WHERE fid = :newsId ")
    void deleteNews(int newsId);
}
