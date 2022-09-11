package com.tejasrafale1.newsfresh

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class NewsListAdapter(val listener: NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {

    val items : ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.item_news,parent,false)
        //inflater to convert xml to view

        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
        //return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]

        Glide.with(holder.itemView.context)
            .load(currentItem.imageUrl).listener(object :RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(holder.image)
        holder.title.text = currentItem.title
        holder.author.text = currentItem.author
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews : ArrayList<News>){ //called by main activity
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged() //call the member function again
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val image: ImageView = itemView.findViewById(R.id.newsimage)
    val title : TextView = itemView.findViewById(R.id.title)
    val author : TextView = itemView.findViewById(R.id.author)
}

interface NewsItemClicked {
    fun onItemClicked(item : News)
}