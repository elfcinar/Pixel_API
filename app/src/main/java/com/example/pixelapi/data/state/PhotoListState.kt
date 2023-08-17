package com.example.pixelapi.data.state

import com.example.pixelapi.data.api.model.Photo

sealed class PhotoListState {
    object Idle:PhotoListState()
    object Empty:PhotoListState()
    object Loading:PhotoListState()
    class Result(val photos:List<Photo>):PhotoListState()
    class Error(val throwable: Throwable):PhotoListState()
}