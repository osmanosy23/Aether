package com.example.aether

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class TabAdapter(
    val items : MutableList<CategoryModel>,
    val context: Context,
//    val categoryClickInterface: CategoryClickInterface,
) : RecyclerView.Adapter<TabAdapter.CategoryViewHolder>(){

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categorybackground : ImageView = itemView.findViewById(R.id.categoryImage)
        val categorytext : TextView = itemView.findViewById(R.id.textCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tab_explore, parent, false)

        return TabAdapter.CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryModel = items[position]
        holder.categorytext.text = categoryModel.category

        Glide.with(holder.itemView.context).load(categoryModel.categoryurl).into(holder.categorybackground)
        holder.itemView.setOnClickListener(View.OnClickListener() {

//            fun onClick(v: View) {
//                categoryClickInterface.onCategoryClick(position);
//            }
    })
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateData(newData:List<CategoryModel>) {
        items.clear()
        items.addAll(newData)

        Log.d("updateTestaaaaa", items.toString())

        notifyDataSetChanged()
    }
public interface  CategoryClickInterface{
    fun onCategoryClick(position : Int);
}

}