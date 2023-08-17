package com.example.pixelapi.data.di

import com.example.pixelapi.Constants
import com.example.pixelapi.data.api.service.PhotoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()


    @Provides
    fun providePhotoService(retrofit:Retrofit):PhotoService = retrofit.create(PhotoService::class.java)
}