package com.example.newsapiapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

package com.example.newsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.api.RetrofitClient
import com.example.newsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val apiKey = "ВСТАВЬ_СВОЙ_API_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loadNews()

        binding.swipeRefresh.setOnRefreshListener {
            loadNews()
        }
    }

    private fun loadNews() {

        lifecycleScope.launch {

            try {

                val response = RetrofitClient.apiService.getTopHeadlines(
                    country = "us",
                    apiKey = apiKey
                )

                if (response.isSuccessful) {

                    val articles = response.body()?.articles ?: emptyList()

                    binding.recyclerView.layoutManager =
                        LinearLayoutManager(this@MainActivity)

                    binding.recyclerView.adapter =
                        NewsAdapter(articles)

                } else {

                    Toast.makeText(
                        this@MainActivity,
                        "Ошибка сервера",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@MainActivity,
                    e.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()

            } finally {

                binding.swipeRefresh.isRefreshing = false
            }
        }
    }
}