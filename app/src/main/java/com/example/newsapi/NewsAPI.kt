package com.example.newsapi

import retrofit2.Call
import retrofit2.http.GET

interface NewsAPI {
    @GET("news/api/news_api.php")
    fun fetchNews(): Call<List<NewsData>>
}