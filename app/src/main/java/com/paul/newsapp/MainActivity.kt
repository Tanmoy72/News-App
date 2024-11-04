package com.paul.newsapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.newsapp.adapters.NewsAdapter
import com.paul.newsapp.api.NewsServiceObj
import com.paul.newsapp.models.ArticleModel
import com.paul.newsapp.models.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var adapter:NewsAdapter
    private lateinit var newsList: RecyclerView
    var pageNum = 1
    var totalResults = -1
    private var articles = mutableListOf<ArticleModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        newsList = findViewById(R.id.newsList)
        adapter = NewsAdapter(this@MainActivity, articles)
        // Set the listener for item changes
        adapter.setOnItemChangeListener(object : NewsAdapter.OnItemChangeListener {
            override fun onItemChanged(position: Int) {
                // Handle the item change here
                //Toast.makeText(this@MainActivity, "Item at position $position changed", Toast.LENGTH_SHORT).show()
                val layoutManager = newsList.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if (totalResults > adapter.itemCount && firstVisibleItemPosition >= adapter.itemCount -5 ){
                    pageNum++
                    getNews()

                }
            }
        })
        newsList.adapter = adapter
        newsList.layoutManager=LinearLayoutManager(this@MainActivity)





        getNews()

    }

    private fun getNews() {
       val news = NewsServiceObj.newsService.getHeadlines("us",pageNum)
        news.enqueue(object: Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
              val news = response.body()
                if (news != null){
                    totalResults = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()

                }

            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.d("TAG","error fetching News ")
                Toast.makeText(this@MainActivity,"Not Found",Toast.LENGTH_SHORT).show()
            }
        })


    }


}