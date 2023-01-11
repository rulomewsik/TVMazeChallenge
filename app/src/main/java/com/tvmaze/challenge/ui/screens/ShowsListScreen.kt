package com.tvmaze.challenge.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.tvmaze.challenge.ui.theme.SecondaryColor
import com.tvmaze.challenge.ui.viewmodels.ShowsListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ShowsListScreen(
    viewModel: ShowsListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val bottomNavigationMenuItems = viewModel.bottomNavigationMenuItems.collectAsState().value
    var selectedBottomNavigationItem by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true) {
        coroutineScope.launch(Dispatchers.IO) {
            viewModel.getAllTVShows()
        }
    }

    BottomNavigation(
        backgroundColor = SecondaryColor
    ) {
        bottomNavigationMenuItems.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.title) },
                selected = selectedBottomNavigationItem == index,
                onClick = { selectedBottomNavigationItem = index }
            )
        }
    }
}