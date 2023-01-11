package com.tvmaze.challenge.domain.usecases

import com.tvmaze.challenge.domain.sources.local.ShowsListSource
import javax.inject.Inject

class ShowsListUseCase @Inject constructor(
    private val showsListSource: ShowsListSource
) {
    fun getBottomNavigationItems() = showsListSource.getBottomNavigationItems()
}