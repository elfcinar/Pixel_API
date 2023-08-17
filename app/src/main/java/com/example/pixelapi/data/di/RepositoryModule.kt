package com.example.pixelapi.data.di

import com.example.pixelapi.data.repository.PhotoRepository
import com.example.pixelapi.data.repository.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePhotoRepository(photoRepositoryImpl: PhotoRepositoryImpl): PhotoRepository = photoRepositoryImpl



}