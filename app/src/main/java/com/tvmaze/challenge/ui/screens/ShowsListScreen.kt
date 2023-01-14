package com.tvmaze.challenge.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.tvmaze.challenge.ui.navigation.goToShowDetailScreen
import com.tvmaze.challenge.ui.theme.DarkGray
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.viewmodels.ShowsListViewModel
import com.tvmaze.challenge.utils.SearchBarState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowsListScreen(
    navController: NavHostController,
    viewModel: ShowsListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val searchBarState by viewModel.searchBarState
    val searchTextState by viewModel.searchTextState
    val showsListPager = viewModel.getTVShowsPagerFlow().collectAsLazyPagingItems()
    val searchShowsList = viewModel.searchShowsList.collectAsState().value

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(id = R.string.shows_navigation),
                searchBarState = searchBarState,
                searchTextState = searchTextState,
                onTextChanged = { viewModel.updateSearchTextState(it) },
                onSearchClicked = {
                    coroutineScope.launch {
                        viewModel.getShowsByName(searchTextState)
                    }
                },
                onSearchTriggered = {
                    viewModel.updateSearchBarState(SearchBarState.OPENED)
                },
                onCloseClicked = {
                    viewModel.updateSearchTextState("")
                    viewModel.updateSearchBarState(SearchBarState.CLOSED)
                })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBlueGreen)
        ) {
            when (searchBarState) {
                SearchBarState.OPENED -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(160.dp),
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            top = 16.dp,
                            end = 8.dp,
                            bottom = 80.dp
                        )
                    ) {
                        items(searchShowsList.size) { index ->
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                elevation = 16.dp,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clickable {
                                        searchShowsList[index].show.let { show ->
                                            if (show != null) {
                                                navController.goToShowDetailScreen(
                                                    show
                                                )
                                            }
                                        }
                                    }
                            ) {
                                Box(
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
                                    val imageUrl = if (searchShowsList[index].show?.image != null) {
                                        searchShowsList[index].show?.image?.original
                                    } else {
                                        ""
                                    }
                                    AsyncImage(
                                        modifier = Modifier.align(Alignment.Center),
                                        model = ImageRequest.Builder(context)
                                            .data(imageUrl)
                                            .crossfade(true)
                                            .build(),
                                        placeholder = painterResource(id = R.drawable.ic_tv_placeholder),
                                        fallback = painterResource(id = R.drawable.ic_tv_placeholder),
                                        error = painterResource(id = R.drawable.ic_tv_placeholder),
                                        contentDescription = searchShowsList[index].show?.name.toString(),
                                        contentScale = ContentScale.Crop,
                                        alpha = 0.6f
                                    )
                                    Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                                        Text(
                                            text = searchShowsList[index].show?.name.toString(),
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
                SearchBarState.CLOSED -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(160.dp),
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            top = 16.dp,
                            end = 8.dp,
                            bottom = 80.dp
                        )
                    ) {
                        items(showsListPager.itemCount) { index ->
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                elevation = 16.dp,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clickable {
                                        showsListPager[index].let { show ->
                                            if (show != null) {
                                                navController.goToShowDetailScreen(
                                                    show
                                                )
                                            }
                                        }
                                    }
                            ) {
                                Box(
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
                                    val imageUrl = if (showsListPager[index]?.image != null) {
                                        showsListPager[index]?.image?.original
                                    } else {
                                        ""
                                    }
                                    AsyncImage(
                                        modifier = Modifier.align(Alignment.Center),
                                        model = ImageRequest.Builder(context)
                                            .data(imageUrl)
                                            .crossfade(true)
                                            .build(),
                                        placeholder = painterResource(id = R.drawable.ic_tv_placeholder),
                                        fallback = painterResource(id = R.drawable.ic_tv_placeholder),
                                        error = painterResource(id = R.drawable.ic_tv_placeholder),
                                        contentDescription = showsListPager[index]?.name.toString(),
                                        contentScale = ContentScale.Crop,
                                        alpha = 0.6f
                                    )
                                    Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                                        Text(
                                            text = showsListPager[index]?.name.toString(),
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
            }
            when (val state = showsListPager.loadState.refresh) {
                is LoadState.Error -> {}
                is LoadState.Loading -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator(color = DarkGray)
                    }
                }
                else -> {}
            }
            when (val state = showsListPager.loadState.append) {
                is LoadState.Error -> {}
                is LoadState.Loading -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator(color = DarkGray)
                    }
                }
                else -> {}
            }
        }
    }
}