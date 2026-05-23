package com.example.newsapiapp.model

import com.google.gson.annotations.SerializedName

class Article(
    val title: String,
    val description: String?,
    @SerializedName("url")
    val urlNews: String,
    @SerializedName("urlToImage")
    val urlImage: String?,
    @SerializedName("publishedAt")
    val publicationTime: String
)