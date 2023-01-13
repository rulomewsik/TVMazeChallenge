package com.tvmaze.challenge.ui.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.theme.PrimaryColor

@Composable
fun DetailAppBar(
    title: String,
    onCloseClicked: () -> Unit
) {
    TopAppBar(
        backgroundColor = PrimaryColor,
        elevation = AppBarDefaults.TopAppBarElevation,
        title = {
            Text(
                text = title,
                color = LightBlueGreen
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onCloseClicked() }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Show Detail",
                    tint = LightBlueGreen
                )
            }
        }
    )
}