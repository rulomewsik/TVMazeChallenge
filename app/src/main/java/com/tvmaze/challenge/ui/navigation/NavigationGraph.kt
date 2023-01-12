package com.tvmaze.challenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tvmaze.challenge.domain.entities.BottomNavigationItem
import com.tvmaze.challenge.ui.screens.FavoritesScreen
import com.tvmaze.challenge.ui.screens.PeopleScreen
import com.tvmaze.challenge.ui.screens.ShowsListScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavigationItem.ShowsListScreen.screen_route) {
        composable(BottomNavigationItem.ShowsListScreen.screen_route) {
            ShowsListScreen()
        }
        composable(BottomNavigationItem.PeopleScreen.screen_route) {
            PeopleScreen()
        }
        composable(BottomNavigationItem.FavoritesScreen.screen_route) {
            FavoritesScreen()
        }
    }
}