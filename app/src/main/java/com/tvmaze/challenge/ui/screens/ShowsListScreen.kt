package com.tvmaze.challenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tvmaze.challenge.ui.theme.DarkGray
import com.tvmaze.challenge.ui.theme.LightBlueGreen
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

    val showsList = viewModel.showsList.collectAsState().value
    val currentShowsPage = remember { mutableStateOf(0) }

//    LaunchedEffect(key1 = true) {
//        coroutineScope.launch(Dispatchers.IO) {
//            viewModel.getAllTVShows()
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlueGreen)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Shows List Screen",
            fontWeight = FontWeight.Bold,
            color = DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            contentPadding = PaddingValues(
                start = 8.dp,
                top = 16.dp,
                end = 8.dp,
                bottom = 16.dp
            )
        ) {
            items(showsList.size) { index ->
                Card(
                    backgroundColor = SecondaryColor,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(250.dp),
                    elevation = 8.dp,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            model = showsList[index].image?.original,
                            contentDescription = null
                        )
                        Text(
                            text = showsList[index].name.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}