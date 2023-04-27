package com.example.anisearch

import AnimeAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.example.anisearch.Anime


class MainActivity : AppCompatActivity() {

    // Declare the RecyclerView, AnimeAdapter, and animeList variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var animeList: ArrayList<Anime>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recycler_view)

        // Set up the GridLayoutManager with 3 columns
        val gridLayoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = gridLayoutManager

        // Populate animeList with data from API, below just testing
        animeList = ArrayList() // Initialize animeList as an empty list
        animeList.add(Anime("Title 1", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 2", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 3", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 4", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 5", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 6", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 7", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 8", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 9", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 10", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 11", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 12", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 13", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 14", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 15", "https://via.placeholder.com/150"))
        animeList.add(Anime("Title 16", "https://via.placeholder.com/150"))


        // Initialize the AnimeAdapter and set it to the RecyclerView
        animeAdapter = AnimeAdapter(animeList)
        recyclerView.adapter = animeAdapter
    }
}
