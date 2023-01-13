package com.tvmaze.challenge.domain.entities

import androidx.compose.runtime.Composable

data class TabRowItem(
    val title: String,
    val screen: @Composable () -> Unit
)