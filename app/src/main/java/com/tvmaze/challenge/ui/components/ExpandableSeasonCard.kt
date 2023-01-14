package com.tvmaze.challenge.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tvmaze.challenge.R
import com.tvmaze.challenge.domain.entities.ExpandableSeasonCardItem
import com.tvmaze.challenge.remote.models.ShowEpisodeModel
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.theme.PrimaryColor
import com.tvmaze.challenge.ui.theme.SecondaryColor
import com.tvmaze.challenge.utils.Constants.EXPAND_ANIMATION_DURATION

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableSeasonCard(
    card: ExpandableSeasonCardItem,
    onCardClick: () -> Unit,
    expanded: Boolean,
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "")
    val cardBgColor by transition.animateColor({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "") {
        if (expanded) PrimaryColor else SecondaryColor
    }
    val cardElevation by transition.animateDp({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "") {
        if (expanded) 24.dp else 4.dp
    }
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "") {
        if (expanded) 180f else 0f
    }

    Card(
        backgroundColor = cardBgColor,
        contentColor = SecondaryColor,
        elevation = cardElevation,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = card.title,
                    modifier = Modifier.padding(16.dp),
                    color = LightBlueGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                IconButton(onClick = { onCardClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "Expandable Arrow",
                        modifier = Modifier
                            .size(48.dp)
                            .rotate(arrowRotationDegree),
                        tint = LightBlueGreen
                    )
                }
            }
            SeasonContent(
                episodes = card.episodes,
                visible = expanded,
                initialVisibility = expanded
            )
        }
    }
}