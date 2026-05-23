package com.example.newsapiapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiapp.model.NewsResponse
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    companion object {
        const val API_KEY = "210f2d2eaa8949dd83b83e8b0d563229"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadNews()
    }

    private fun loadNews() {

        val url =
            "https://newsapi.org/v2/top-headlines?country=us&apiKey=$API_KEY"

        val request = Request.Builder().url(url).build()

        ApiClient.client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Ошибка: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {

                    val json = response.body?.string()
                    val newsResponse = Gson().fromJson(json, NewsResponse::class.java)

                    runOnUiThread {
                        recyclerView.adapter = NewsAdapter(newsResponse.articles)
                    }
                }
            })
    }
}