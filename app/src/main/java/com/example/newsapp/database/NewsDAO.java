package com.example.newsapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.newsapp.Models.News;

import java.util.List;

@Dao
public interface NewsDAO {
    @Insert
    void insertNews(News news);

    @Query("SELECT * FROM news11")
    List<News> getListNews();
    @Query("DELETE FROM news11 WHERE news_id = :newsId ")
    void deleteNews(int newsId);
    @Query("SELECT COUNT(*) FROM news11 where title = :desiredArticle")
    int checkIfArticleExists(String desiredArticle);
}
