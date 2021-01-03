package com.example.newsapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "NewsFetcher"
class NewsFetcher {
    private val newApi: NewsAPI
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.106/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newApi = retrofit.create(NewsAPI::class.java)
    }

    fun fetchNews(): LiveData<List<NewsData>> {
        val responseLiveData: MutableLiveData<List<NewsData>> = MutableLiveData()
        val newRequest: Call<List<NewsData>> = newApi.fetchNews()
        newRequest.enqueue(object : Callback<List<NewsData>> {
            override fun onFailure(call: Call<List<NewsData>>, t: Throwable) {
                Log.e(TAG, "Failed to fetch news", t)
            }
            override fun onResponse(
                call: Call<List<NewsData>>,
                response: Response<List<NewsData>>
            ) {
                Log.d(TAG, "Response received")
                val appResponse: List<NewsData>? = response.body()
                var newItems: List<NewsData> = appResponse
                    ?: mutableListOf()

                responseLiveData.value = newItems
            }
        })
        return responseLiveData
    }
}

