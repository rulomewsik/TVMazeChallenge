package com.tvmaze.challenge.ui.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun MainTopAppBar(
    title: String,
    onSearchClick: () -> Unit
) {
    val context = LocalContext.current

    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(Icons.Filled.Search, contentDescription = "Search TV show")
            }
        }
    )
}