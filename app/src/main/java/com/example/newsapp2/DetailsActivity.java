package com.example.newsapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp2.model.Article;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity
{
    Article News;
    TextView titlev,author,time,content;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // back button code
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // receive data which is sending pre.. activity
        News = (Article) getIntent().getSerializableExtra("data");
        titlev = findViewById(R.id.title_t);
        author = findViewById(R.id.author);
        time = findViewById(R.id.time);
        content = findViewById(R.id.content);
        image =findViewById(R.id.image);

        titlev.setText(News.getTitle());
        author.setText(News.getAuthor());
        time.setText(News.getPublishedAt());
        content.setText(News.getDescription());
        Picasso.get().load(News.getUrlToImage()).into(image);
    }

    // go back button implementation
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}