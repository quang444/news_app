package com.example.newsapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import retrofit2.http.HeaderMap;

@Entity(tableName = "favourite")
public class favourite {
    @PrimaryKey(autoGenerate = true)
    public  int fid;
    @ColumnInfo(name = "uId")
    String userId;
    @Embedded
    News news;

    public int getId() {
        return fid;
    }

    public void setId(int id) {
        this.fid = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
