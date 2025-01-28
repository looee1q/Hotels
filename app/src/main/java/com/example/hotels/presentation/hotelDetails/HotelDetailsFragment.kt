package com.example.hotels.presentation.hotelDetails

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil3.ImageLoader
import coil3.asDrawable
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.transformations
import coil3.size.Scale
import coil3.transform.RoundedCornersTransformation
import com.example.hotels.R
import com.example.hotels.databinding.FragmentHotelDetailsBinding
import com.example.hotels.presentation.bindingfragment.BindingFragment
import com.example.hotels.presentation.utils.ImageTooSmallException
import com.example.hotels.presentation.utils.ImageUtils
import com.example.hotels.presentation.utils.ImageUtils.clipTransformation
import com.example.hotels.presentation.utils.convertPxToDp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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
                binding.contentContainer.isVisible = false
            }

            is HotelDetailsScreenState.Loaded -> {
                binding.contentContainer.isVisible = true
                val hotel = screenState.hotelDetails
                Log.d(
                    "HotelDetailsFragment",
                    "state is HotelDetailsScreenState.Loaded. Hotel is $hotel"
                )
                binding.hotelName.text = hotel.name
                showRating(hotel.stars.toInt())
                lifecycleScope.launch {
                    loadImage(hotel.imageUrl)
                }
                binding.availableSuitesValue.text = hotel.suitesAvailability.size.toString()
                binding.addressValue.text = hotel.address
                binding.viewOnMapButton.setOnClickListener {
                    sendIntentToMapApps(
                        latitude = hotel.latitude,
                        longitude = hotel.longitude
                    )
                }
            }


            HotelDetailsScreenState.NoConnectionError -> {
                binding.contentContainer.isVisible = false

            }

            HotelDetailsScreenState.UnknownError -> {
                binding.contentContainer.isVisible = false

            }
        }
    }

    private fun showRating(rating: Int) {
        if (rating == 0) {
            binding.noInfoAboutRating.isVisible = true
        } else {
            repeat(rating) {
                val star = ImageView(requireContext()).also {
                    it.setImageResource(R.drawable.baseline_star_24)
                    it.layoutParams = ViewGroup.LayoutParams(
                        convertPxToDp(context = requireContext(), pixels = 40),
                        convertPxToDp(context = requireContext(), pixels = 40)
                    )
                }
                binding.ratingContainer.addView(star)
            }
        }
    }

    private suspend fun loadImage(imageUrl: String?) {
        val request = ImageRequest.Builder(requireContext())
            .data(imageUrl)
            .transformations(
                clipTransformation(),
                RoundedCornersTransformation(24f)
            )
            .scale(Scale.FILL)
            .listener(
                onStart = { binding.imageProgressBar.isVisible = true },
                onError = { _, _ -> binding.imageProgressBar.isVisible = false },
                onSuccess = { _, _ -> binding.imageProgressBar.isVisible = false }
            )
            .build()
        val imageLoader = ImageLoader(requireContext())
        when (val result = imageLoader.execute(request)) {
            is SuccessResult -> {
                onImageSuccessResult(result)
            }

            is ErrorResult -> {
                onImageErrorResult()
            }
        }
    }

    private fun onImageSuccessResult(result: SuccessResult) {
        try {
            ImageUtils.validateImageSize(
                width = result.image.width,
                height = result.image.height,
                minWidth = ImageUtils.MIN_HOTELS_IMAGE_WIDTH,
                minHeight = ImageUtils.MIN_HOTELS_IMAGE_HEIGHT,
            )

            binding.hotelImage.isVisible = true
            binding.unavailableImageContainer.isVisible = false

            val drawable = result.image.asDrawable(resources)
            binding.hotelImage.setImageDrawable(drawable)
        } catch (exception: ImageTooSmallException) {
            Log.e(ImageUtils.TOO_SMALL_IMAGE_TAG, "${exception.message}", exception)
            onImageErrorResult()
        }
    }

    private fun onImageErrorResult() {
        binding.hotelImage.isVisible = false
        binding.unavailableImageContainer.isVisible = true
    }

    private fun sendIntentToMapApps(latitude: Double, longitude: Double) {
        val mapIntent = Uri.parse("geo:$latitude,$longitude").let { location ->
            Intent(Intent.ACTION_VIEW, location)
        }
        try {
            startActivity(mapIntent)
        } catch (exception: ActivityNotFoundException) {
            Log.e("Sending Intent Failed", "${exception.message}", exception)
        }
    }
}
