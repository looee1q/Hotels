package com.example.hotels.presentation.hotels.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hotels.R
import com.example.hotels.domain.models.SortingType
import com.example.hotels.presentation.utils.mapToString

@Composable
fun HeaderWithSorting(
    modifier: Modifier = Modifier,
    onSortingTypeClickListener: (SortingType) -> Unit
) {
    Header(
        modifier = modifier
    ) {
        SortingDropdownMenu(
            onSortingTypeClickListener = { onSortingTypeClickListener(it) }
        )
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_hotel_24),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                text = stringResource(R.string.hotels_app),
                style = MaterialTheme.typography.titleLarge
            )
            content?.invoke()
        }
        ShadowImitation()
    }
}

@Composable
private fun ShadowImitation(
    modifier: Modifier = Modifier,
    elevation: Dp = 8.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(elevation)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.1f),
                        Color.Transparent
                    )
                )
            )
    )
}

@Composable
private fun SortingDropdownMenu(
    modifier: Modifier = Modifier,
    onSortingTypeClickListener: (SortingType) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedOption by rememberSaveable { mutableStateOf<SortingType?>(null) }
    Box(modifier = modifier) {
        Button(
            onClick = { expanded = !expanded },
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.sort),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = if (selectedOption != null) Color.Cyan else Color.Unspecified
                    )
                )
                Icon(
                    painter = painterResource(R.drawable.baseline_sort_24),
                    contentDescription = null,
                    tint = if (selectedOption != null) Color.Cyan else LocalContentColor.current
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                SortingType.entries.forEach {
                    val dropdownMenuItemColor = if (selectedOption == it) {
                        Color.Blue
                    } else {
                        Color.Unspecified
                    }
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = it.mapToString(context = LocalContext.current),
                                style = MaterialTheme.typography.bodyMedium.copy(color = dropdownMenuItemColor)
                            )
                        },
                        onClick = {
                            onSortingTypeClickListener(it)
                            selectedOption = it
                        }
                    )
                }
            }
        }
    }
}
