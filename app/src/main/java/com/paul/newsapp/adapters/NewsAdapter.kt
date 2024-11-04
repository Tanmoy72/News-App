package com.paul.newsapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.paul.newsapp.R
import com.paul.newsapp.activity.DetailActivity
import com.paul.newsapp.models.ArticleModel

class NewsAdapter(val context: Context,val articles: List<ArticleModel>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    // Define an interface for the listener
    interface OnItemChangeListener {
        fun onItemChanged(position: Int)
    }

    private var itemChangeListener: OnItemChangeListener? = null

    fun setOnItemChangeListener(listener: OnItemChangeListener) {
        itemChangeListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.item_view, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return articles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, article.title, Toast.LENGTH_SHORT).show()
            itemChangeListener?.onItemChanged(position)
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }
    }
    class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)

    }
}