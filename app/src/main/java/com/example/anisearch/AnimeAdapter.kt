import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anisearch.R
import com.example.anisearch.Anime
import com.example.anisearch.SecondPage

// Define AnimeAdapter class to extend RecyclerView.Adapter
class AnimeAdapter(private val animeList: List<Anime>) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    // fill layout for each anime item and create new AnimeViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        return AnimeViewHolder(view)
    }

    // Bind data from the animeList to the views in AnimeViewHolder
    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]
        holder.animeTitle.text = anime.animeTitle
        Glide.with(holder.itemView.context).load(anime.imageURL).fitCenter().into(holder.animeImage)
        //On click listen will get the Anime data to pass to the Second Page class and start the Activity
        holder.animeImage.setOnClickListener {
            val intent = Intent(holder.itemView.context, SecondPage::class.java)
            intent.putExtra("animeTitle", anime.animeTitle)
            intent.putExtra("imageURL", anime.imageURL)
            intent.putExtra("animeRank", anime.animeRank.toString())
            intent.putExtra("animeStatus", anime.animeStatus)
            intent.putExtra("animeDescription", anime.animeDescription)
            holder.itemView.context.startActivity(intent)
        }

    }

    // Return number of items in the animeList
    override fun getItemCount(): Int {
        return animeList.size
    }

    // Define AnimeViewHolder class to extend RecyclerView.ViewHolder
    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Declare and initialize ImageView and TextView for each anime item
        val animeImage: ImageView = itemView.findViewById(R.id.anime_image)
        val animeTitle: TextView = itemView.findViewById(R.id.anime_title)
    }
}
