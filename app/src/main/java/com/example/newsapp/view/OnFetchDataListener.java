package com.example.newsapp.view;

import com.example.newsapp.Models.News;

import java.io.Serializable;
import java.util.List;

public interface OnFetchDataListener<NewsApiResponse>  {
    void onFecthData(List<News> list, String message);
    void onError(String message);
}
