package com.example.newsapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsApi_Fragement :Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java) }
    
    
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragement_news_api, container, false)
        newRecyclerView = view.findViewById(R.id.news_recycler_view)
        newRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.newItemLiveData.observe(
            viewLifecycleOwner,
            Observer { newsItems ->
                newRecyclerView.adapter = NewsAdapter(newsItems)

            })
    }

    private class NewsHolder(itemTextView: View) : RecyclerView.ViewHolder(itemTextView) {

        val titleTextView = itemTextView.findViewById(R.id.title) as TextView
        val detailsTextView=itemTextView.findViewById(R.id.details ) as TextView

        fun bind(news:NewsData){
            titleTextView.setText(news.News_Title)
            detailsTextView.setText(news.News_Text)

        }
    }
    private class NewsAdapter(private val newsItems: List<NewsData>) : RecyclerView.Adapter<NewsHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): NewsHolder {
            val View = LayoutInflater.from(parent.context).inflate(R.layout.news_details,parent,false)
            return NewsHolder(View)
        }
        override fun getItemCount(): Int = newsItems.size
        override fun onBindViewHolder(holder: NewsHolder, position: Int) {
            val newItem = newsItems[position]
            holder.bind(newItem)
        }
    }

    companion object {
        fun newInstance() = NewsApi_Fragement()
    }
}