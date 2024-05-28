package com.example.newsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.newsapp.Models.News;
import com.example.newsapp.Models.NewsApiResponse;
import com.example.newsapp.R;
import com.example.newsapp.retrofit.APIService;
import com.example.newsapp.adapter.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements SelectListener {
    RecyclerView recyclerView;
    SearchView searchView;
    CustomAdapter adapter;

    List<News> listNews = new ArrayList<>();

    Context context;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.seach_view1);

        context = getApplicationContext();
        dialog = new ProgressDialog(this);
        dialog.setTitle("loading...<3");
        dialog.show();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Call<NewsApiResponse> call = APIService.ApiService.searchNews( query, null, null, "0cc29eea5cdf4e789ff606920b241cd3");
                call.enqueue(new Callback<NewsApiResponse>() {
                    @Override
                    public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().getStatus().equals("ok"))
                                showNews(response.body().getArticle());
                            dialog.dismiss();
                        } else {
                            Log.e("response error", "response is not successful");
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsApiResponse> call, Throwable throwable) {
                        Log.e("API Error", "API call failed", throwable);

                    }
                });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private void showNews(List<News> list) {
        listNews = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listNews.addAll(list);
        adapter = new CustomAdapter(context, listNews,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void OnNewsClicked(News news) {

    }

    @Override
    public void OnNewsLongcClicked(News news) {

    }
}



