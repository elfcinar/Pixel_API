package com.example.pixelapi.ui

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.load
import com.example.pixelapi.data.api.model.Photo
import com.example.pixelapi.data.repository.PhotoRepository
import com.example.pixelapi.data.state.PhotoListState
import com.example.pixelapi.ui.adapter.AdapterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) :ViewModel() {
    private var score = 0
    private var firstIndex = 0
    private lateinit var firstPhoto:Photo
    lateinit var photos:MutableList<Photo>
    private var clickedPhotoId:Int = -1
    private val _photoListState:MutableStateFlow<PhotoListState> = MutableStateFlow(PhotoListState.Idle)
    val photoListState:StateFlow<PhotoListState> = _photoListState

    private val _adapterState:MutableStateFlow<AdapterState> = MutableStateFlow(AdapterState.Idle)
    val adapterState:StateFlow<AdapterState> = _adapterState


    fun getAllPhotos(queryText:String){
        viewModelScope.launch {
            if(!queryText.isNullOrEmpty()) {
                runCatching {
                    _photoListState.emit(PhotoListState.Loading)
                    photos = photoRepository.getAllPhotos(queryText).take(5).toMutableList()
                    photos += photos
                    photos.shuffle()
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


    fun onClick(photo:Photo, index:Int){
        viewModelScope.launch {

            if(_photoListState.value is PhotoListState.Result){
                if(clickedPhotoId > -1){
                    photo.selected = true
                    println(index)
                    if(photo.id == clickedPhotoId){
                        val photos = (_photoListState.value as PhotoListState.Result).photos.toMutableList()
                        _adapterState.value = AdapterState.ChangedAll(index,firstIndex)
                        score += 5
                        println(score)
                        delay(2000)
                        photos.removeAt(firstIndex)
                        val currentIndex = photos.indexOf(photo)
                        photos.removeAt(currentIndex)
                        _adapterState.value = AdapterState.Removed(currentIndex,firstIndex)
                    }else{
                        _adapterState.value = AdapterState.Changed(index)
                        delay(2000)
                        photo.selected = false
                        firstPhoto.selected = false
                        _adapterState.value = AdapterState.ChangedAll(index,firstIndex)
                    }
                    clickedPhotoId = -1
                }else{
                    photo.selected = true
                    _adapterState.value = AdapterState.Changed(index)
                    firstIndex = index
                    println(firstIndex)
                    firstPhoto = photo
                    clickedPhotoId = photo.id
                }
            }
        }
    }
}