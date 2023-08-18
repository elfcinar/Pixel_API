package com.example.pixelapi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
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
        if(photo.selected){
            holder.ivPhoto.load(photo.src.portrait)
        }else{
            holder.ivPhoto.load("https://images.pexels.com/photos/2310713/pexels-photo-2310713.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
        }
        holder.itemView.setOnClickListener {
            onClick(photo)


        }
    }
}