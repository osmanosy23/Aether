import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.example.aether.R


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aether.NewsDetailActivity
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class NewsAdapter(
    val items: MutableList<News>,
    val context: Context,
    val activity: ActivityResultLauncher<Intent>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleView: TextView = itemView.findViewById(R.id.articletitleText)
        val author: TextView = itemView.findViewById(R.id.articleauthorText)
        val date: TextView = itemView.findViewById(R.id.articledateText)
        val image: ImageView = itemView.findViewById(R.id.newsImage)

        val cardView: CardView = itemView.findViewById(R.id.myCardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_cell, parent, false)

        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = items[position]
        holder.titleView.text = currentNews.title
        holder.author.text = currentNews.author
        val zoneId = ZoneId.of("US/Eastern")
        val instant = (Instant.parse(currentNews.date))
        val locale = Locale.ENGLISH
        val zdt = instant.atZone(zoneId)
        val format = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(locale)
        holder.date.text = zdt.format(format)


        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(currentNews.imageurl)
            .fitCenter()
            .placeholder(R.drawable.telescope_vec)
            .into(holder.image);

        val intentLauncher = activity
        holder.cardView.setOnClickListener {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra("title", currentNews.title)
            intent.putExtra("author", currentNews.author)
            intent.putExtra("image", currentNews.imageurl)
            intent.putExtra("date", currentNews.date)
            intent.putExtra("description", currentNews.description)
            intent.putExtra("url", currentNews.url)

            intent.putExtra("pos", position)

            intentLauncher.launch(intent)
            notifyItemChanged(position)

            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newData: List<News>) {
        items.clear()
        items.addAll(newData)


        notifyDataSetChanged()
    }
}


