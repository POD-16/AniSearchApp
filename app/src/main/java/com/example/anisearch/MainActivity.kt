package com.example.anisearch

import AnimeAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    //Create mutable list for each Anime and recycler view
    private lateinit var animeList: MutableList<Anime>
    private lateinit var rvAnime: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnime = findViewById(R.id.recycler_view)
        animeList = mutableListOf()

        getAnimeData()
    }

    private fun getAnimeData() {

        val client = AsyncHttpClient()
        val url = "https://api.jikan.moe/v4/top/anime"
        client[url, object : JsonHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {

                val animeArray = json?.jsonObject?.getJSONArray("data")

                for (i in 0 until (animeArray?.length() ?: 0)) {
                    val anime = Anime("", "")
                    val animeObject = animeArray?.getJSONObject(i)
                    val imagesObject = animeObject?.getJSONObject("images")
                    anime.imageURL = imagesObject?.getJSONObject("jpg")?.getString("image_url") ?: ""
                    anime.animeTitle = animeObject?.getString("title") ?: ""
                    animeList.add(anime)
                }

                val adapter = AnimeAdapter(animeList)
                rvAnime.adapter = adapter
                rvAnime.layoutManager = GridLayoutManager(this@MainActivity, 3)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("API Error", errorResponse)
            }

        }]

    }
}
