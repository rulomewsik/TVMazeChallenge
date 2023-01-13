package com.tvmaze.challenge.ui.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.theme.PrimaryColor
import com.tvmaze.challenge.utils.SearchBarState

@Composable
fun MainTopAppBar(
    title: String,
    searchBarState: SearchBarState,
    searchTextState: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    onCloseClicked: () -> Unit,
) {
    when (searchBarState) {
        SearchBarState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = { onTextChanged(it) },
                onCloseClicked = { onCloseClicked() },
                onSearchClicked = { onSearchClicked(it) }
            )
        }
        SearchBarState.CLOSED -> {
            TopAppBar(
                backgroundColor = PrimaryColor,
                elevation = AppBarDefaults.TopAppBarElevation,
                title = {
                    Text(
                        text = title,
                        color = LightBlueGreen
                    )
                },
                actions = {
                    IconButton(
                        onClick = { onSearchTriggered() }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search TV Show",
                            tint = LightBlueGreen
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun MainTopAppBarPreview() {
    MainTopAppBar(
        title = "Lorem Ipsum",
        searchBarState = SearchBarState.CLOSED,
        searchTextState = "",
        onTextChanged = { },
        onSearchClicked = { },
        onSearchTriggered = { },
        onCloseClicked = { })
}