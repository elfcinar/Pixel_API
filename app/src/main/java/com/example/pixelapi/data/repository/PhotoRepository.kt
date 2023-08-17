package com.example.pixelapi.data.repository

import com.example.pixelapi.data.api.model.Photo

interface PhotoRepository {

    suspend fun getAllPhotos(queryText:String):List<Photo>
}