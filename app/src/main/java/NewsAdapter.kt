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
    val items : MutableList<News>,
    val context: Context,
    val activity : ActivityResultLauncher<Intent>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    class NewsViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val titleView : TextView = itemView.findViewById(R.id.articletitleText)
        val author : TextView = itemView.findViewById(R.id.articleauthorText)
        val date : TextView = itemView.findViewById(R.id.articledateText)
        val image : ImageView = itemView.findViewById(R.id.newsImage)

        val cardView : CardView = itemView.findViewById(R.id.myCardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
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
//        holder.date.text=currentNews.date
        Glide.with(holder.itemView.context).load(currentNews.imageurl).into(holder.image)

        val intentLauncher = activity
        holder.cardView.setOnClickListener {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra("title",currentNews.title)
            intent.putExtra("author",currentNews.author)
            intent.putExtra("image",currentNews.imageurl)
            intent.putExtra("date",currentNews.date)
            intent.putExtra("description",currentNews.description)
            intent.putExtra("url",currentNews.url)

            intent.putExtra("pos",position)

            intentLauncher.launch(intent)
            notifyItemChanged(position)

            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateData(newData:List<News>){
        items.clear()
        items.addAll(newData)

        Log.d("updateTestaaaaa", items.toString())

        notifyDataSetChanged()
    }
}
////    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
////        val titleText: TextView = view.findViewById(R.id.titleText)
////        val date: TextView = view.findViewById(R.id.dateText)
////        val author: TextView = view.findViewById(R.id.authorTextView)
////
////        val image: ImageView = itemView.findViewById(R.id.imageView)
////        val cardView: CardView = view.findViewById(R.id.myCardView)
//private val items: ArrayList<News> = ArrayList()
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
//        //        view.setOnClickListener{
////            listener.onItemClicked(items[viewHolder.adapterPosition])
//
//        return NewsViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        val currentItem = items[position]
//        holder.titleView.text = currentItem.title
//        holder.author.text = currentItem.author
//        Glide.with(holder.itemView.context).load(currentItem.imageurl).into(holder.image)
//    }
//
//    fun updateNews(updatedNews: ArrayList<News>) {
//        items.clear()
//        items.addAll(updatedNews)
//
//        notifyDataSetChanged()
//    }
//}
//
//class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    val titleView: TextView = itemView.findViewById(R.id.title)
//    val image: ImageView = itemView.findViewById(R.id.image)
//    val author: TextView = itemView.findViewById(R.id.author)
//}
//
//interface NewsItemClicked {
//    fun onItemClicked(item: News)
//}


//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
//        val view =
//            LayoutInflater.from(viewGroup.context).inflate(R.layout.notes_cell, viewGroup, false)
//        return ViewHolder(view)
//    }


//    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//        viewHolder.titleText.text = dataSet[position].title
////        viewHolder.date.text = dataSet[position].date
//        viewHolder.author.text = dataSet[position].author
//
//        viewHolder.image
//
//
//    }
//
//
//    override fun getItemCount() = dataSet.size

