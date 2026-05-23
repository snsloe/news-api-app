package com.example.newsapiapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapiapp.databinding.ArticleBinding
import com.example.newsapiapp.model.Article

class NewsAdapter(private val articles: List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(
        val binding: ArticleBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {

        val article = articles[position]

        holder.binding.textTitle.text = article.title
        holder.binding.textDescription.text = article.description
        holder.binding.textDate.text = article.publicationTime

        Glide.with(holder.itemView.context).load(article.urlImage).into(holder.binding.imageNews)

        holder.itemView.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW, article.urlNews.toUri())

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}