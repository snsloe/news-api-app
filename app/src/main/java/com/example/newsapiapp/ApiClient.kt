package com.example.newsapiapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object ApiClient {

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(logging).build()
    }
}