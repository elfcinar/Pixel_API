package com.example.pixelapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixelapi.data.repository.PhotoRepository
import com.example.pixelapi.data.state.PhotoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) :ViewModel() {

    private val _photoListState:MutableStateFlow<PhotoListState> = MutableStateFlow(PhotoListState.Idle)
    val photoListState:StateFlow<PhotoListState> = _photoListState


    fun getAllPhotos(queryText:String){
        viewModelScope.launch {
            if(!queryText.isNullOrEmpty()) {
                runCatching {
                    _photoListState.emit(PhotoListState.Loading)
                    val photos = photoRepository.getAllPhotos(queryText)
                    if (photos.isNullOrEmpty()) {
                        _photoListState.emit(PhotoListState.Empty)
                    } else {
                        _photoListState.value = PhotoListState.Result(photos)
                    }
                }.onFailure {
                    _photoListState.value = PhotoListState.Error(it)
                }
            }else{

            }
        }
    }
}