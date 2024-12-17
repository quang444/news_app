package com.example.newsapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newsapp.Models.News;
import com.example.newsapp.Models.favourite;
import com.example.newsapp.R;
import com.example.newsapp.database.NewsDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    News news;
    TextView txt_title, txt_author, txt_time, txt_detail, txt_content;
    ImageView img_news;
    ImageButton btn_like, btn_back;
    String userId;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
             userId = user.getUid();
        } else {
            Toast.makeText(this, "Vui lòng đăng nhập để lưu bài viết", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        txt_title = findViewById(R.id.text_detail_title);
        txt_author = findViewById(R.id.text_detail_author);
        txt_time = findViewById(R.id.text_detail_time);
        txt_detail = findViewById(R.id.text_detail_detail);
        txt_content = findViewById(R.id.text_detail_content);
        img_news = findViewById(R.id.img_detail_news);


        news = (News) getIntent().getSerializableExtra("data");
        txt_title.setText(news.getTitle());
        txt_author.setText(news.getAuthor());
        txt_time.setText(news.getPublishedAt());
        txt_detail.setText(news.getTitle());
        txt_content.setText(news.getContent());
        Picasso.get().load(news.getUrlToImage()).into(img_news);

        btn_like = findViewById(R.id.btn_like);
        btn_back = findViewById(R.id.btn_back);

        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId == null) {
                    Toast.makeText(DetailsActivity.this, "Vui lòng đăng nhập để lưu bài viết", Toast.LENGTH_SHORT).show();
                    return;
                }

                int count = NewsDatabase.getInstance(DetailsActivity.this).favouriteDAO().checkIfArticleExists(news.getTitle(), userId);
                if (count > 0) {
                    Toast.makeText(DetailsActivity.this, "Bài viết đã được thêm vào danh sách lưu!!!", Toast.LENGTH_SHORT).show();
                } else {
                    favourite f = new favourite();
                    f.setNews(news);
                    f.setUserId(userId);
                    NewsDatabase.getInstance(DetailsActivity.this).favouriteDAO().insertNews(f);
                    Toast.makeText(DetailsActivity.this, "Thêm vào danh sách lưu thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }



}
