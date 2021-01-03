package com.example.newsapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {
    val newItemLiveData: LiveData<List<NewsData>>
    init {
        newItemLiveData = NewsFetcher().fetchNews()
    }
}