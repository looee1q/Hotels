package com.example.hotels.presentation.hotelDetails

import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil3.ImageLoader
import coil3.load
import coil3.request.ImageRequest
import coil3.request.target
import coil3.request.transformations
import coil3.size.Size
import coil3.transform.Transformation
import com.example.hotels.databinding.FragmentHotelDetailsBinding
import com.example.hotels.presentation.bindingfragment.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HotelDetailsFragment : BindingFragment<FragmentHotelDetailsBinding>() {

    private val viewModel by viewModels<HotelDetailsViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHotelDetailsBinding {
        return FragmentHotelDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.screenState.onEach {
            renderScreenState(it)
        }.launchIn(lifecycleScope)
    }

    private fun renderScreenState(screenState: HotelDetailsScreenState) {
        when (screenState) {
            HotelDetailsScreenState.Initial -> {}
            HotelDetailsScreenState.Loading -> {

            }

            is HotelDetailsScreenState.Loaded -> {
                val hotel = screenState.hotelDetails
                Log.d(
                    "HotelDetailsFragment",
                    "state is HotelDetailsScreenState.Loaded. Hotel is $hotel"
                )
                binding.hotelName.text = hotel.name
                loadImage(hotel.imageUrl)
            }


            HotelDetailsScreenState.NoConnectionError -> {

            }

            HotelDetailsScreenState.UnknownError -> {

            }
        }
    }

    private fun loadImage(imageUrl: String?) {
        val request = ImageRequest.Builder(requireContext())
            .data(imageUrl)
            .transformations(clipTransformation())
            .target(binding.hotelImage)
            .build()
        ImageLoader(requireContext()).enqueue(request)
    }

}



fun clipTransformation() = object : Transformation() {
    override val cacheKey: String = "2_dp_crop"

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        val width = input.width
        val height = input.height
        val croppedBitmap = Bitmap.createBitmap(input, 2, 2, width - 4, height - 4)
        input.recycle()
        return croppedBitmap
    }

}
