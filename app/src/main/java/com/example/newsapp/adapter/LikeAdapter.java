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
import com.example.newsapp.Models.favourite;
import com.example.newsapp.R;
import com.example.newsapp.view.SelectListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.MyViewholder> {
    private Context context;
    private List<favourite> favourites;
    SelectListener listener;
    String  userId;

    public LikeAdapter(Context context, List<favourite> favourites, SelectListener listener, String userId) {
        this.context = context;
        this.favourites = favourites;
        this.listener = listener;
        this.userId = userId;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.headline_list_item, parent, false);
        return new MyViewholder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.text_title.setText(favourites.get(position).getNews().getTitle());
        holder.text_source.setText(favourites.get(position).getNews().getSource().getName());
        if(favourites.get(position).getNews().getUrlToImage() != null){
            Picasso.get().load(favourites.get(position).getNews().getUrlToImage()).into(holder.img_headline);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnNewsClicked(favourites.get(position).getNews());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.OnNewsLongcClicked(favourites.get(position));
                return true;

            }
        });

    }

    @Override
    public int getItemCount() {
        return favourites.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder{
        TextView text_title, text_source;
        ImageView img_headline;
        CardView cardView;


        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.text_title);
            text_source = itemView.findViewById(R.id.text_source);
            img_headline = itemView.findViewById(R.id.img_headline);
            cardView = itemView.findViewById(R.id.main_container);

        }
    }


}
