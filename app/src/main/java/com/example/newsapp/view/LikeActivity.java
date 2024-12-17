package com.example.newsapp.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Models.News;
import com.example.newsapp.Models.favourite;
import com.example.newsapp.R;
import com.example.newsapp.adapter.LikeAdapter;
import com.example.newsapp.database.NewsDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class LikeActivity extends AppCompatActivity implements SelectListener {
    RecyclerView recyclerView;
    Dialog dialog;
    Button btn_delete, btn_cancel;
    News news;
    String userId;
    LikeAdapter likeAdapter ;
    List<favourite> favourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewed);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        } else {
            Toast.makeText(this, "Vui lòng đăng nhập để lưu bài viết", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        favourites = NewsDatabase.getInstance(LikeActivity.this).favouriteDAO().getAllNewsForUser(userId);
        likeAdapter = new LikeAdapter(LikeActivity.this, favourites, this, userId);
        recyclerView = findViewById(R.id.recycler_like);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(likeAdapter);
    }


    @Override
    public void OnNewsClicked(News news) {
        Intent intent = new Intent(LikeActivity.this,DetailsActivity.class);
        intent.putExtra("data",news);
        startActivity(intent);

    }

    @Override
    public void OnNewsLongcClicked(favourite favourites) {

        deleteNewsFromDatabase(favourites.getId());
    }


    public void deleteNewsFromDatabase(final int id) {
        dialog = new Dialog(LikeActivity.this);
        dialog.setContentView(R.layout.dialog_delete);
        btn_cancel = dialog.findViewById(R.id.btn_cancel);
        btn_delete = dialog.findViewById(R.id.btn_del);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsDatabase.getInstance(LikeActivity.this).favouriteDAO().deleteNews(id);
                Toast.makeText(LikeActivity.this,"Xoa thanh cong ", Toast.LENGTH_SHORT).show();
                favourites.removeIf(favourite -> favourite.getId() == id );
                likeAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
