package com.tvmaze.challenge.ui.components

import android.content.res.Resources
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tvmaze.challenge.R
import com.tvmaze.challenge.remote.models.ShowEpisodeModel
import com.tvmaze.challenge.ui.theme.DarkGray
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.viewmodels.ShowDetailViewModel
import com.tvmaze.challenge.utils.Constants.EXPANSION_TRANSITION_DURATION
import kotlin.math.roundToInt

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SeasonContent(
    episodes: List<ShowEpisodeModel>,
    visible: Boolean = true,
    initialVisibility: Boolean = false,
    onEpisodeClicked: () -> Unit,
    viewModel: ShowDetailViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(EXPANSION_TRANSITION_DURATION)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(EXPANSION_TRANSITION_DURATION)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            // Expand from the top.
            shrinkTowards = Alignment.Top,
            animationSpec = tween(EXPANSION_TRANSITION_DURATION)
        ) + fadeOut(
            // Fade in with the initial alpha of 0.3f.
            animationSpec = tween(EXPANSION_TRANSITION_DURATION)
        )
    }
    AnimatedVisibility(
        visible = visible,
        initiallyVisible = initialVisibility,
        enter = enterTransition,
        exit = exitTransition
    ) {
        val episodesColumnHeight =
            (episodes.size * 8 * Resources.getSystem().displayMetrics.density).roundToInt()

        LazyColumn(
            modifier = Modifier.height(episodesColumnHeight.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(episodes.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LightBlueGreen)
                        .clickable {
                            viewModel.onEpisodeClicked(episodes[index])
                            onEpisodeClicked()
                                   },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(episodes[index].image?.medium)
                            .crossfade(false)
                            .build(),
                        placeholder = painterResource(id = R.drawable.ic_tv),
                        contentDescription = episodes[index].name.toString(),
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = episodes[index].number.toString() + "  |",
                        modifier = Modifier.padding(start = 16.dp),
                        color = DarkGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = episodes[index].name.toString(),
                        modifier = Modifier.padding(start = 8.dp),
                        color = DarkGray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }
}