package com.example.pixelapi.ui.photoDetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.pixelapi.data.api.model.Photo
import com.example.pixelapi.databinding.FragmentPostDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PhotoDetailFragment(val photo:Photo) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentPostDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(layoutInflater,container, false)
        // binding.root.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}