package com.example.hotels.presentation.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.example.hotels.databinding.FragmentHotelsBinding
import com.example.hotels.presentation.bindingfragment.BindingFragment
import com.example.hotels.presentation.hotels.ui.HotelsScreen
import com.example.hotels.presentation.navigation.HotelDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelsFragment : BindingFragment<FragmentHotelsBinding>() {

    private val viewModel by viewModels<HotelsViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHotelsBinding {
        return FragmentHotelsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setContent {
            MaterialTheme {
                val screenState by viewModel.screenState.collectAsStateWithLifecycle()
                HotelsScreen(
                    hotelsScreenState = screenState,
                    onHotelClickListener = { navigateToHotelDetails(hotelId = it.id) },
                    onSortingTypeClickListener = { viewModel.sortHotels(it) },
                    onRetryButtonClickListener = { viewModel.retryGetHotels() }
                )
            }
        }
    }

    private fun navigateToHotelDetails(hotelId: Int) {
        findNavController().navigate(route = HotelDetails(id = hotelId))
    }
}
