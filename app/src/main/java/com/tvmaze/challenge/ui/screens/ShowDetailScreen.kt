package com.tvmaze.challenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.tvmaze.challenge.R
import com.tvmaze.challenge.domain.entities.TabRowItem
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.ui.components.EpisodeContent
import com.tvmaze.challenge.ui.navigation.DetailAppBar
import com.tvmaze.challenge.ui.theme.DarkGray
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.theme.SecondaryColor
import com.tvmaze.challenge.ui.viewmodels.ShowDetailViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun ShowDetailScreen(
    navController: NavHostController,
    show: TVShowModel?,
    viewModel: ShowDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    val bannerImage = viewModel.showBannerImage.collectAsState().value
    val episodeSelected = viewModel.episodeSelected.collectAsState().value

    val tabRowItems = listOf(
        TabRowItem(
            title = stringResource(id = R.string.show_detail_information_title),
            screen = { DetailTabScreen(show) }
        ),
        TabRowItem(
            title = stringResource(id = R.string.show_detail_episodes_title),
            screen = {
                EpisodesTabScreen(
                    viewModel,
                    onEpisodeClicked = {
                        coroutineScope.launch {
                            bottomSheetState.expand()
                        }
                    })
            }
        )
    )

    LaunchedEffect(key1 = true) {
        show?.id?.let {
            viewModel.getShowImages(it)
            viewModel.getShowEpisodes(it)
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DetailAppBar(
                title = show?.name.toString(),
                onCloseClicked = { navController.popBackStack() })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightBlueGreen)
                    .padding(it)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
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
                            .data(bannerImage.resolutions?.original?.url)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(id = R.drawable.ic_tv),
                        contentDescription = show?.name.toString(),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop
                    )
                }
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                            color = MaterialTheme.colors.secondary
                        )
                    },
                ) {
                    tabRowItems.forEachIndexed { index, item ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                            text = {
                                Text(
                                    text = item.title,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        )
                    }
                }
                HorizontalPager(
                    count = tabRowItems.size,
                    state = pagerState,
                ) {
                    tabRowItems[pagerState.currentPage].screen()
                }
            }
        },
        sheetContent = {
            EpisodeContent(
                episode = episodeSelected,
                onCloseClicked = {
                    coroutineScope.launch {
                        bottomSheetState.collapse()
                    }
                }
            )
        },
        sheetPeekHeight = 0.dp,
        sheetElevation = 16.dp
    )
}