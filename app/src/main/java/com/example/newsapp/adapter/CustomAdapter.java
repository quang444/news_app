package com.example.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Models.News;
import com.example.newsapp.R;
import com.example.newsapp.view.SelectListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private List<News> news;
    SelectListener listener;

    public CustomAdapter(Context context, List<News> news, SelectListener listener) {
        this.context = context;
        this.news = news;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.headline_list_item , parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text_title.setText(news.get(position).getTitle());
        holder.text_source.setText(news.get(position).getSource().getName());
        if(news.get(position).getUrlToImage()!= null){
            Picasso.get().load(news.get(position).getUrlToImage()).into(holder.img_headline);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnNewsClicked(news.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {

        return news.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_title, text_source;
        ImageView img_headline;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.text_title);
            text_source = itemView.findViewById(R.id.text_source);
            img_headline = itemView.findViewById(R.id.img_headline);
            cardView = itemView.findViewById(R.id.main_container);

        }
    }
}
