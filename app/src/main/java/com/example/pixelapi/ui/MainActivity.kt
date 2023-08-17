package com.example.pixelapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pixelapi.R
import com.example.pixelapi.data.api.model.Photo
import com.example.pixelapi.data.state.PhotoListState
import com.example.pixelapi.databinding.ActivityMainBinding
import com.example.pixelapi.ui.adapter.PhotoListAdapter
import com.example.pixelapi.ui.photoDetail.PhotoDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listeners()
        observePhotoListState()

    }

    private fun listeners() {
        binding.btnSearch.setOnClickListener {
            viewModel.getAllPhotos(binding.etSearch.text.toString())
        }
    }

    private fun observePhotoListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.photoListState.collect{
                    when(it){
                        PhotoListState.Idle ->{}
                        PhotoListState.Empty ->{
                            binding.progressBar.isVisible = false
                            binding.rvPhotos.isVisible = false
                            binding.tvSearch.isVisible = true
                            binding.tvSearch.text = "No result"
                        }
                        PhotoListState.Loading ->{
                            binding.progressBar.isVisible = true
                            binding.rvPhotos.isVisible = false
                            binding.tvSearch.isVisible = false
                        }
                        is PhotoListState.Result ->{
                            binding.progressBar.isVisible = false
                            binding.rvPhotos.adapter = PhotoListAdapter(this@MainActivity, it.photos,this@MainActivity::onClick)
                            binding.rvPhotos.isVisible = true
                            binding.tvSearch.isVisible = true
                            binding.tvSearch.text = binding.etSearch.text.toString()
                            binding.etSearch.text.clear()
                        }
                        is PhotoListState.Error ->{
                            binding.progressBar.isVisible = false
                            binding.rvPhotos.isVisible = false
                            binding.tvSearch.isVisible = false
                        }
                    }
                }
            }
        }
    }

    private fun onClick(photo: Photo){
        val photoDetailFragment = PhotoDetailFragment(photo)
        //photoDetailFragment.show()
    }
}