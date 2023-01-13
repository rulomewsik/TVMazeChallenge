package com.tvmaze.challenge.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tvmaze.challenge.domain.entities.BottomNavigationItem
import com.tvmaze.challenge.ui.theme.DarkGray
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.theme.PrimaryColor

@Composable
fun BottomNavigation(navController: NavHostController) {
    val screenItems = listOf(
        BottomNavigationItem.ShowsListScreen,
        BottomNavigationItem.PeopleScreen,
        BottomNavigationItem.FavoritesScreen,
    )

    val context = LocalContext.current

    BottomNavigation(
        backgroundColor = PrimaryColor,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screenItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = {
                    Text(
                        stringResource(
                            id = context.resources.getIdentifier(
                                item.title,
                                "string",
                                context.packageName
                            )
                        )
                    )
                },
                selected = currentRoute == item.screen_route,
                selectedContentColor = LightBlueGreen,
                unselectedContentColor = DarkGray,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}