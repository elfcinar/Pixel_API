package com.example.pixelapi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pixelapi.data.api.model.Photo
import com.example.pixelapi.databinding.PhotoListItemBinding

class PhotoListAdapter(val context:Context, val photos:List<Photo>,val onClick:(photo:Photo) ->Unit):RecyclerView.Adapter<PhotoListAdapter.MyViewHolder>() {


    class MyViewHolder(view:PhotoListItemBinding):ViewHolder(view.root){
        val ivPhoto = view.ivPhoto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(PhotoListItemBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val photo = photos[position]
        holder.ivPhoto.load(photo.src.portrait)
        holder.itemView.setOnClickListener {
            onClick(photo)
        }
    }
}