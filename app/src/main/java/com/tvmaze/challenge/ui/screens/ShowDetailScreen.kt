package com.tvmaze.challenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.tvmaze.challenge.R
import com.tvmaze.challenge.domain.entities.TabRowItem
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.ui.navigation.DetailAppBar
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.theme.SecondaryColor
import com.tvmaze.challenge.ui.viewmodels.ShowsListViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ShowDetailScreen(
    navController: NavHostController,
    show: TVShowModel?,
    viewModel: ShowsListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val showsListPager = viewModel.getTVShowsPagerFlow().collectAsLazyPagingItems()
    val searchShowsList = viewModel.searchShowsList.collectAsState().value

    val tabRowItems = listOf(
        TabRowItem(
            title = stringResource(id = R.string.show_detail_information_title),
            screen = { DetailTabScreen(navController = navController, show = show) }
        ),
        TabRowItem(
            title = stringResource(id = R.string.show_detail_episodes_title),
            screen = { EpisodesTabScreen(navController = navController, show = show) }
        )
    )
    Scaffold(
        topBar = {
            DetailAppBar(
                title = show?.name.toString(),
                onCloseClicked = { navController.popBackStack() })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBlueGreen)
                .padding(it)
        ) {
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
    }
}