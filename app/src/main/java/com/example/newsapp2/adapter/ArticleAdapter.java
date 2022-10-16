package com.example.newsapp2.adapter;



import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp2.R;
import com.example.newsapp2.details.SelectListener;
import com.example.newsapp2.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder>
{
    private List<Article> articleList;
    private SelectListener selectListener;


    public ArticleAdapter(List<Article> articleList, SelectListener selectListener)

    {
        this.articleList = articleList;
        this.selectListener = selectListener;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lidt_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        if(!TextUtils.isEmpty(articleList.get(position).getTitle()))

        {
            holder.titlev.setText(articleList.get(position).getTitle());
        }

        if(!TextUtils.isEmpty(articleList.get(position).getDescription()))

        {
            holder.descriptionv.setText(articleList.get(position).getDescription());
        }

        if(articleList.get(position).getUrlToImage()!=null) {

            Picasso.get().load(articleList.get(position).getUrlToImage()).into(holder.img_headline);
        }

        holder.cardview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectListener.OnNewsClicked(articleList.get(position));
            }
        });


    }



    @Override
    public int getItemCount()
    {
        return articleList.size();
    }




    public class ViewHolder extends  RecyclerView.ViewHolder
    {
        private TextView titlev , descriptionv;
        ImageView img_headline;
        CardView cardview;


        public ViewHolder(@NonNull View itemView)

        {
            super(itemView);
            titlev = itemView.findViewById(R.id.title);
            descriptionv = itemView.findViewById(R.id.description);
            img_headline = itemView.findViewById(R.id.img_headline);
            cardview = itemView.findViewById(R.id.cardView);
        }
    }


}
