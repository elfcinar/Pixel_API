package com.example.pixelapi.data.repository

import com.example.pixelapi.data.api.model.Photo
import com.example.pixelapi.data.api.service.PhotoService
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoService: PhotoService
):PhotoRepository {
    override suspend fun getAllPhotos(queryText:String): List<Photo> {
        return photoService.getAllPhotos(queryText).photos
    }
}