package com.example.newsapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {
    private ImageView imgavata;
    private TextView tvname, tvemail;
    private Button bntlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initUi();
        showInformation();

        bntlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AccountActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUi(){
        imgavata = findViewById(R.id.img_avata);
        tvname = findViewById(R.id.tv_name);
        tvemail = findViewById(R.id.tv_email);
        bntlogout = findViewById(R.id.btn_logout);
    }
    private void showInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            if (name == null) {
                tvname.setVisibility(View.GONE);
            } else {
                tvname.setVisibility(View.VISIBLE);
                tvname.setText(name);
            }

            tvemail.setText(email);
            Glide.with(this).load(photoUrl).error(R.drawable.user_logo).into(imgavata);
        } else {
            // Handle the case where user is not logged in
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            // Optionally, redirect to login screen
        }
    }


}