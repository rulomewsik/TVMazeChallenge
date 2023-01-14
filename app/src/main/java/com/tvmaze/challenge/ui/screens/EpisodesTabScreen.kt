package com.tvmaze.challenge.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.viewmodels.ShowDetailViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EpisodesTabScreen(
    navController: NavHostController,
    show: TVShowModel?,
    viewModel: ShowDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlueGreen)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Episodes",
            style = MaterialTheme.typography.body1,
        )
    }
}