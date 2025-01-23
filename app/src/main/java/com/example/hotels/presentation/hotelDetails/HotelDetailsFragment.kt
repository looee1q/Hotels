package com.example.hotels.presentation.hotelDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hotels.databinding.FragmentHotelDetailsBinding
import com.example.hotels.presentation.bindingfragment.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

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


    }

}
