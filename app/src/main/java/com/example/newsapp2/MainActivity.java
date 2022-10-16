package com.example.newsapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.newsapp2.adapter.ArticleAdapter;
import com.example.newsapp2.details.SelectListener;
import com.example.newsapp2.model.Article;
import com.example.newsapp2.model.ResponseModel;
import com.example.newsapp2.rests.ApiClient;
import com.example.newsapp2.rests.ApiInterface;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SelectListener  {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    ArticleAdapter adapter;

    String query;

    private static final String API_KEY = "b41fcde7233e404491c169359f2f661f";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // back button show
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar2);



        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager( layoutManager);
       progressBar.setVisibility(View.VISIBLE);




         final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
         Call<ResponseModel> call = apiService.getLatestNews("techcrunch",API_KEY);

         call.enqueue(new Callback<ResponseModel>()
         {

             @Override
             public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response)
             {
                 assert response.body() != null;
                 if(response.body().getStatus().equals("ok"))
                 {
                     List<Article> articleList = response.body().getArticles();

                     if(articleList.size()>0)
                     {
                         //Log.i(TAG, "My name is Sohail Anwar  " + articleList.size());
                         adapter = new ArticleAdapter(articleList,MainActivity.this);
                         recyclerView.setLayoutManager( layoutManager);
                         recyclerView.setAdapter(adapter);
                         progressBar.setVisibility(View.GONE);
                     }

                 }

             }
             @Override
             public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t)
             {
                 Log.e("out", t.toString());
             }
         });

    }

    @Override
    public void OnNewsClicked(Article News)
    {
        // sending data to details activity

        startActivity(new Intent(MainActivity.this,DetailsActivity.class)
                .putExtra("data", News));

    }



}