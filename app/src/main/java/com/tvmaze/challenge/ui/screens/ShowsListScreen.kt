package com.tvmaze.challenge.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tvmaze.challenge.R
import com.tvmaze.challenge.ui.navigation.BottomNavigation
import com.tvmaze.challenge.ui.navigation.MainTopAppBar
import com.tvmaze.challenge.ui.theme.DarkGray
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.theme.SecondaryColor
import com.tvmaze.challenge.ui.viewmodels.ShowsListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowsListScreen(
    navController: NavHostController,
    viewModel: ShowsListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val showsList = viewModel.getTVShowsByPage(0).collectAsLazyPagingItems()
    val currentShowsPage = remember { mutableStateOf(0) }


//    LaunchedEffect(key1 = showsListLastIndex) {
//        viewModel.getTVShowsByPage(currentShowsPage.value)
//    }

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(id = R.string.shows_navigation),
                onSearchClick = {

                }
            )
        },
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBlueGreen)
                .wrapContentSize(Alignment.Center)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                contentPadding = PaddingValues(
                    start = 8.dp,
                    top = 16.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
            ) {
                items(showsList.itemCount) { index ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(200.dp),
                        elevation = 16.dp,
                    ) {
                        Box(
                            contentAlignment = Alignment.BottomCenter,
                            modifier = Modifier
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black
                                        )
                                    )
                                )
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(showsList[index]?.image?.original)
                                    .crossfade(true)
                                    .build(),
                                placeholder = painterResource(id = R.drawable.ic_tv),
                                contentDescription = showsList[index]?.name.toString(),
                                contentScale = ContentScale.Crop,
                                alpha = 0.6f
                            )
                            Text(
                                text = showsList[index]?.name.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
                when (val state = showsList.loadState.refresh) {
                    is LoadState.Error -> {
                        //TODO Error Item
                        //state.error to get error message
                    }
                    is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                CircularProgressIndicator(color = DarkGray)
                            }
                        }
                    }
                    else -> {}
                }
                when (val state = showsList.loadState.append) {
                    is LoadState.Error -> {
                        //TODO Pagination Error Item
                        //state.error to get error message
                    }
                    is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                CircularProgressIndicator(color = DarkGray)
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}