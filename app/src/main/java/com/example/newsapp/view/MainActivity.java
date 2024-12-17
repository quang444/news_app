package com.example.newsapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.example.newsapp.Models.News;
import com.example.newsapp.Models.NewsApiResponse;
import com.example.newsapp.Models.favourite;
import com.example.newsapp.R;
import com.example.newsapp.retrofit.APIService;
import com.example.newsapp.adapter.CustomAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SelectListener, OnclickListener{

    RecyclerView recyclerView;
    CustomAdapter adapter;

    List<News> listNews = new ArrayList<>();

    Context context;
    ProgressDialog dialog;
    Button b1,b2,b3,b4,b5,b6,b7;
    BottomNavigationView nav;
    SearchView searchView;
     MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         nav = findViewById(R.id.bottom_navigation);
         myBroadcastReceiver = new MyBroadcastReceiver();


        context = getApplicationContext();
        dialog = new ProgressDialog(this);
        dialog.setTitle("loading...");
        dialog.show();

        b1 = findViewById(R.id.btn_business);
        b2 = findViewById(R.id.btn_entertainment);
        b3 = findViewById(R.id.btn_general);
        b4 = findViewById(R.id.btn_health);
        b5 = findViewById(R.id.btn_science);
        b6 = findViewById(R.id.btn_sports);
        b7= findViewById(R.id.btn_technology);

        b1.setOnClickListener(v -> {
            this.Onclick(b1);
        });
        b2.setOnClickListener(v -> {
            this.Onclick(b2);
        });
        b3.setOnClickListener(v -> {
            this.Onclick(b3);
        });
        b4.setOnClickListener(v -> {
            this.Onclick(b4);
        });
        b5.setOnClickListener(v -> {
            this.Onclick(b5);
        });
        b6.setOnClickListener(v -> {
            this.Onclick(b6);
        });
        b7.setOnClickListener(v -> {
            this.Onclick(b7);
        });
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_favourite){
                    Intent intent = new Intent(MainActivity.this,LikeActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (item.getItemId()==R.id.menu_search) {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(intent);
                    return true;

                } else if (item.getItemId() == R.id.menu_account) {
                    Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                    startActivity(intent);
                    return true;

                }
                else if (item.getItemId() == R.id.menu_news){
                    return true;
                }
                return true;
            }
        });


        Call<NewsApiResponse> call = APIService.ApiService.callNews("us", "general", null, "0cc29eea5cdf4e789ff606920b241cd3");
        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getStatus().equals("ok"))
                        showNews(response.body().getArticle());
                        dialog.dismiss();
                    }
                 else {
                    Log.e("Response Error", "Response is not successful");
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable throwable) {

            }
        });
//
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myBroadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }

    private void showNews(List<News> list) {
        listNews = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listNews.addAll(list);
        adapter = new CustomAdapter(context, listNews,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void OnNewsClicked(News news) {
        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
        intent.putExtra("data",news);
        startActivity(intent);
    }

    @Override
    public void OnNewsLongcClicked(favourite favourites) {

    }

    @Override
    public void Onclick(Button btn) {
        System.out.println(btn.getText().toString());
        Call<NewsApiResponse> call = APIService.ApiService.callNews("us", btn.getText().toString(), null, "0cc29eea5cdf4e789ff606920b241cd3");
        dialog.setTitle("loading "+ btn.getText().toString());
        dialog.show();
        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getStatus().equals("ok"))
                        showNews(response.body().getArticle());
                    dialog.dismiss();

                }
                else {
                    Log.e("Response Error", "Response is not successful");
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable throwable) {
                Log.e("API Error", "API call failed", throwable);
            }
        });


    }

}





















