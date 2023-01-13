package com.tvmaze.challenge.ui.navigation

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.tvmaze.challenge.domain.entities.NavigationItem
import com.tvmaze.challenge.domain.entities.NavigationItem.Companion.SHOW_DETAIL_ROUTE
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.ui.screens.FavoritesScreen
import com.tvmaze.challenge.ui.screens.PeopleScreen
import com.tvmaze.challenge.ui.screens.ShowDetailScreen
import com.tvmaze.challenge.ui.screens.ShowsListScreen
import com.tvmaze.challenge.utils.ShowDetailParamType

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = NavigationItem.ShowsListScreen.screen_route) {
        composable(NavigationItem.ShowsListScreen.screen_route) {
            ShowsListScreen(navController)
        }
        composable(
            route = NavigationItem.ShowDetailScreen.screen_route,
            arguments = listOf(
                navArgument("show") {
                    type = ShowDetailParamType()
                }
            )
        ) {
            val show = it.arguments?.getParcelable<TVShowModel>("show")
            ShowDetailScreen(navController, show)
        }
        composable(NavigationItem.PeopleScreen.screen_route) {
            PeopleScreen()
        }
        composable(NavigationItem.FavoritesScreen.screen_route) {
            FavoritesScreen()
        }
    }
}

fun NavHostController.goToShowDetailScreen(show: TVShowModel) {
    val showJson = Uri.encode(Gson().toJson(show))
    navigate("${SHOW_DETAIL_ROUTE}$showJson")
}