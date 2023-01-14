package com.tvmaze.challenge.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.ui.components.ExpandableSeasonCard
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.viewmodels.ShowDetailViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EpisodesTabScreen(
    show: TVShowModel?,
    viewModel: ShowDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val seasonCards = viewModel.seasonCards.collectAsState().value
    val expandedSeasonCards = viewModel.expandedSeasonCards.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlueGreen)
            .padding(24.dp)
    ) {
        LazyColumn {
            items(seasonCards.size) { index ->
                ExpandableSeasonCard(
                    card = seasonCards[index],
                    onCardClick = { viewModel.onSeasonCardArrowClicked(seasonCards[index].id) },
                    expanded = expandedSeasonCards.contains(seasonCards[index].id),
                )
            }
        }
    }
}