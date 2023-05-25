package com.example.examapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examapplication.Constants
import com.example.examapplication.R
import com.example.examapplication.model.Movie
import com.squareup.picasso.Picasso

class Adapter(var list : List<Movie>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.movieImage)
        var title = itemView.findViewById<TextView>(R.id.movieTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(list[position].original_title)
        Picasso.get().load(Constants.IMAGE_BASE_URL+list[position].poster_path).into(holder.image);
    }
}