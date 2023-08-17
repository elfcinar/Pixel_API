package com.example.pixelapi.data.api.service

import com.example.pixelapi.Constants
import com.example.pixelapi.data.api.model.Photo
import com.example.pixelapi.data.api.model.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotoService {


    @Headers("Authorization: ${Constants.API_KEY}")

    @GET("v1/search")
    suspend fun getAllPhotos(@Query("query") queryText:String): Response<List<Photo>>
}