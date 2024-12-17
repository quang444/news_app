package com.example.newsapp.view;

import com.example.newsapp.Models.News;
import com.example.newsapp.Models.favourite;

import java.io.Serializable;
import java.util.List;

public interface SelectListener  {
    void OnNewsClicked(News news);
    void OnNewsLongcClicked(favourite favourites);
}
