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

    @Query("SELECT * FROM news")
    List<News> getListNews();
}