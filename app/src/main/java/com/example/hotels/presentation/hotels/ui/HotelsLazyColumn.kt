package com.example.hotels.presentation.hotels.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hotels.R
import com.example.hotels.domain.models.HotelInfo
import com.example.hotels.ui.theme.Typography

@Composable
fun HotelsList(
    modifier: Modifier = Modifier,
    hotels: List<HotelInfo>,
    onHotelClickListener: (HotelInfo) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(hotels) { hotelInfo ->
            HotelItem(
                hotelInfo = hotelInfo,
                onHotelClickListener = { onHotelClickListener(hotelInfo) }
            )
        }
    }
}

@Composable
private fun HotelItem(
    modifier: Modifier = Modifier,
    hotelInfo: HotelInfo,
    onHotelClickListener: (HotelInfo) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White, shape = RoundedCornerShape(24.dp))
                .padding(16.dp)
                .clickable { onHotelClickListener(hotelInfo) }
        ) {
            Text(
                text = hotelInfo.name,
                style = Typography.titleLarge
            )
            Text(
                text = hotelInfo.address,
                style = Typography.bodyLarge.copy(color = Color.Gray)
            )
            HotelStars(
                modifier = Modifier.height(20.dp),
                hotelStars = hotelInfo.stars.toInt()
            )
            Text(
                text = stringResource(R.string.meters_from_center, hotelInfo.distance),
                style = Typography.bodyMedium
            )
            Text(
                text = if (hotelInfo.suitesAvailability.isEmpty()) {
                    stringResource(R.string.no_suites_available)
                } else {
                    pluralStringResource(
                        R.plurals.suites_available,
                        hotelInfo.suitesAvailability.size,
                        hotelInfo.suitesAvailability.size
                    )
                },
                style = Typography.bodyMedium
            )
        }
    }
}

@Composable
private fun HotelStars(
    modifier: Modifier = Modifier,
    hotelStars: Int,
    maxStars: Int = 5,
    starsSize: Dp = 14.dp
) {
    if (hotelStars > maxStars || hotelStars < 0) {
        throw IllegalArgumentException("hotelStars cannot be negative or higher than maxStars")
    }
    Row(
        modifier = modifier.background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(hotelStars) {
            Icon(
                modifier = Modifier.size(starsSize),
                imageVector = Icons.Filled.Star,
                contentDescription = null
            )
        }
        repeat(maxStars - hotelStars) {
            Icon(
                modifier = Modifier.size(starsSize),
                imageVector = Icons.Filled.StarOutline,
                contentDescription = null,
            )
        }
    }
}
