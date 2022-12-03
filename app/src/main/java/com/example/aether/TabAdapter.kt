package com.example.aether

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

class TabAdapter(
    val items: MutableList<CategoryModel>,
    val context: Context,
    val activity: ActivityResultLauncher<Intent>

) : RecyclerView.Adapter<TabAdapter.CategoryViewHolder>() {


    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class CategoryViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val categorybackground: ImageView = itemView.findViewById(R.id.categoryImage)
        val categorytext: TextView = itemView.findViewById(R.id.textCategory)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tab_explore, parent, false)

        return CategoryViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryModel = items[position]
        holder.categorytext.text = categoryModel.category

        Glide.with(holder.itemView.context).load(categoryModel.categoryurl)
            .into(holder.categorybackground)
        if (position == 2) {
            val intentLauncher = activity
            holder.categorybackground.setOnClickListener {
                val intent = Intent(context, NewYoutubeActivity::class.java)
                intent.putExtra("pos", position)

                intentLauncher.launch(intent)
                notifyItemChanged(position)

                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newData: List<CategoryModel>) {
        items.clear()
        items.addAll(newData)


        notifyDataSetChanged()
    }


}