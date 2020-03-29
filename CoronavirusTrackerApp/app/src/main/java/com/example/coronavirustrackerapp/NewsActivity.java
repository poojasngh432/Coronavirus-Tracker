package com.example.coronavirustrackerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coronavirustrackerapp.adapter.NewsAdapter;
import com.example.coronavirustrackerapp.api.Api;
import com.example.coronavirustrackerapp.model.Article;
import com.example.coronavirustrackerapp.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity {
    private List<Article> newsdata;
    private Context mContext;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mContext = NewsActivity.this;
       callApi();

    }

    private void setRecyclerView() {
        if(newsdata != null){
            newsAdapter = new NewsAdapter(newsdata, mContext);
            recyclerView = findViewById(R.id.news_rv);
            layoutManager = new LinearLayoutManager(NewsActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);
            newsAdapter = new NewsAdapter(newsdata, mContext);
            recyclerView.setAdapter(newsAdapter);
            newsAdapter.notifyDataSetChanged();
            initListener();
        }
    }

    private void callApi() {
        showLoadingDialog();
        //Creating a retrofit object

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL_NEWS)
                .addConverterFactory(GsonConverterFactory.create())  //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        Api api = retrofit.create(Api.class);
        Call<News> call = api.getNews();

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                dismissLoadingDialog();
                if (response.isSuccessful() && response.body().getArticles() != null) {
                    newsdata = response.body().getArticles();
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                    setRecyclerView();
                }
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
                dismissLoadingDialog();
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initListener(){

        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageView imageView = view.findViewById(R.id.img);
                Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);

                Article article = newsdata.get(position);
                intent.putExtra("url", article.getUrl());
                intent.putExtra("title", article.getTitle());
                intent.putExtra("img",  article.getUrlToImage());
                intent.putExtra("date",  article.getPublishedAt());
                intent.putExtra("source",  article.getSource().getName());
                intent.putExtra("author",  article.getAuthor());

//                Pair<View, String> pair = Pair.create((View)imageView, ViewCompat.getTransitionName(imageView));
//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        MainActivity.this, pair
//                );

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                     startActivity(intent, optionsCompat.toBundle());
//                }else {
                    startActivity(intent);
              //  }

            }
        });

    }

    private void showLoadingDialog() {
        progressDialog = ProgressDialog.show(mContext, null, this.getString(R.string.loading), false, false);
    }

    private void dismissLoadingDialog() {
        try {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            progressDialog = null;
        }
    }
}
