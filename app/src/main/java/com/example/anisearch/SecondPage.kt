package com.example.anisearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class SecondPage : AppCompatActivity() {

    private lateinit var animePic: ImageView
    private lateinit var animeRank: TextView
    private lateinit var animeTitles: TextView
    private lateinit var animeStatus: TextView
    private lateinit var animeDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)

        animePic = findViewById(R.id.anime_pic)
        animeRank = findViewById(R.id.anime_rank)
        animeTitles = findViewById(R.id.anime_titles)
        animeStatus = findViewById(R.id.anime_status)
        animeDesc = findViewById(R.id.anime_desc)

        val imageURL = intent.getStringExtra("imageURL")
        val title = intent.getStringExtra("animeTitle")
        val rank = intent.getStringExtra("animeRank")
        Log.d("debug", rank.toString())
        val status = intent.getStringExtra("animeStatus")
        val description = intent.getStringExtra("animeDescription")
        //Load image
        Glide.with(this).load(imageURL).fitCenter().into(animePic)
        //Assign data to the proper Text Views in the second page activity
        animeTitles.text = title
        animeRank.text = if(rank!!.toInt() == 0) "Rank: N/A" else "Rank: $rank"
        animeStatus.text = status
        animeDesc.text = description
    }
}