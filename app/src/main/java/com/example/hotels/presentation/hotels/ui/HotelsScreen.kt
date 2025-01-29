package com.example.hotels.presentation.hotels.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hotels.R
import com.example.hotels.domain.models.SortingType
import com.example.hotels.domain.models.HotelInfo
import com.example.hotels.presentation.hotels.HotelsScreenState

@Composable
fun HotelsScreen(
    hotelsScreenState: HotelsScreenState,
    onSortingTypeClickListener: (SortingType) -> Unit,
    onHotelClickListener: (HotelInfo) -> Unit,
    onRetryButtonClickListener: () -> Unit = { }
) {
    when (hotelsScreenState) {
        HotelsScreenState.Initial -> {}

        HotelsScreenState.Loading -> LoadingState()

        is HotelsScreenState.Loaded -> {
            LoadedState(
                hotels = hotelsScreenState.hotels,
                onSortingTypeClickListener = { onSortingTypeClickListener(it) },
                onHotelClickListener = { onHotelClickListener(it) }
            )
        }

        HotelsScreenState.EmptyList -> {
            EmptyListState(
                onRetryButtonClickListener = { onRetryButtonClickListener() }
            )
        }

        HotelsScreenState.NoConnectionError -> {
            NoConnectionErrorState(
                onRetryButtonClickListener = { onRetryButtonClickListener() }
            )
        }

        HotelsScreenState.UnknownError -> {
            UnknownErrorState(
                onRetryButtonClickListener = { onRetryButtonClickListener() }
            )
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Header()
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun LoadedState(
    hotels: List<HotelInfo>,
    onSortingTypeClickListener: (SortingType) -> Unit,
    onHotelClickListener: (HotelInfo) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderWithSorting(
            onSortingTypeClickListener = { onSortingTypeClickListener(it) }
        )
        HotelsList(
            hotels = hotels,
            onHotelClickListener = { onHotelClickListener(it) }
        )
    }
}

@Composable
private fun EmptyListState(
    onRetryButtonClickListener: () -> Unit
) {
    NoDataState(
        onRetryButtonClickListener = { onRetryButtonClickListener() },
        imageVectorIcon = Icons.Filled.SearchOff,
        stateDescription = stringResource(R.string.nothing_found)
    )
}

@Composable
private fun NoConnectionErrorState(
    onRetryButtonClickListener: () -> Unit
) {
    NoDataState(
        onRetryButtonClickListener = { onRetryButtonClickListener() },
        imageVectorIcon = Icons.Filled.SignalWifiConnectedNoInternet4,
        stateDescription = stringResource(R.string.no_connection)
    )
}

@Composable
private fun UnknownErrorState(
    onRetryButtonClickListener: () -> Unit
) {
    NoDataState(
        onRetryButtonClickListener = { onRetryButtonClickListener() },
        imageVectorIcon = Icons.Filled.ErrorOutline,
        stateDescription = stringResource(R.string.unknown_error)
    )
}

@Composable
private fun NoDataState(
    onRetryButtonClickListener: () -> Unit,
    imageVectorIcon: ImageVector,
    stateDescription: String,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Header()
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = imageVectorIcon,
                contentDescription = stateDescription
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stateDescription,
                style = MaterialTheme.typography.displaySmall
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.BottomCenter),
            onClick = { onRetryButtonClickListener() }
        ) {
            Text(
                text = stringResource(R.string.retry),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
