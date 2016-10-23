package com.example.hoang.thenews.Api;

/**
 * Created by hoang on 10/18/16.
 */

import com.example.hoang.thenews.model.Articles;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by hoang on 10/18/2016.
 */

public interface ArticleApi {
    final String BASE_URL = "http://api.nytimes.com/svc/search/v2/";

    @GET(BASE_URL + "articlesearch.json" )
    Call<Articles> getArticle(@Query("q") String yourSearch, @Query("page") String curentPage, @Query("api-key") String yourApi);

    class Factory {
        private static ArticleApi service;

        public static ArticleApi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(ArticleApi.class);
                return service;
            } else {
                return service;
            }
        }
    }
}

