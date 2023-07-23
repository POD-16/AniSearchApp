package com.example.anisearch

import AnimeAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
class MainActivity : AppCompatActivity() {
    //Create mutable list for each Anime and create recycler view variable
    private lateinit var animeList: MutableList<Anime>
    private lateinit var rvAnime: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnime = findViewById(R.id.recycler_view)
        animeList = mutableListOf()
        //Text listener for the search bar to get the user input
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchAnime(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })


        getAnimeData()

    }
    //getAnimeDate function to fetch the data from the Jikan API
    private fun getAnimeData() {

        val client = AsyncHttpClient()
        val url = "https://api.jikan.moe/v4/top/anime"
        client[url, object : JsonHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {

                val animeArray = json?.jsonObject?.getJSONArray("data")
                //for loop to go through the anime array to get data and assign them to the Anime attributes
                for (i in 0 until (animeArray?.length() ?: 0)) {
                    val anime = Anime("", "", 0, "", "")
                    val animeObject = animeArray?.getJSONObject(i)
                    val imagesObject = animeObject?.getJSONObject("images")
                    anime.imageURL = imagesObject?.getJSONObject("jpg")?.getString("image_url") ?: ""
                    anime.animeTitle = animeObject?.getString("title") ?: ""
                    anime.animeRank = animeObject?.getInt("rank") ?: 0
                    anime.animeStatus = animeObject?.getString("status") ?: ""
                    anime.animeDescription = animeObject?.getString("synopsis") ?: ""
                    animeList.add(anime)
                }
                //Set adapter
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
    //searchAnime function to search for an anime based on the users input
    private fun searchAnime(query: String) {
        val client = AsyncHttpClient()
        val url = "https://api.jikan.moe/v4/anime?q=$query"

        client[url, object : JsonHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {

                val animeArray = json?.jsonObject?.getJSONArray("data")
                //Removes all elements from the anime list
                animeList.clear()
                //for loop to go through the anime array to get data and assign them to the Anime attributes
                for (i in 0 until (animeArray?.length() ?: 0)) {
                    val anime = Anime("", "", 0, "", "")
                    //Get each Anime from the anime array
                    val animeObject = animeArray?.getJSONObject(i)
                    val imagesObject = animeObject?.getJSONObject("images")
                    anime.imageURL = imagesObject?.getJSONObject("jpg")?.getString("image_url") ?: ""
                    anime.animeTitle = animeObject?.getString("title") ?: ""
                    anime.animeRank = animeObject?.optInt("rank", 0)
                    anime.animeStatus = animeObject?.getString("status") ?: ""
                    anime.animeDescription = animeObject?.getString("synopsis") ?: ""
                    //Add each Anime to the anime list
                    animeList.add(anime)
                }
                //Set adapter
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
