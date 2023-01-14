package com.tvmaze.challenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tvmaze.challenge.R
import com.tvmaze.challenge.remote.models.ShowEpisodeModel
import com.tvmaze.challenge.ui.theme.DarkGray
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import java.text.ParseException
import java.text.SimpleDateFormat

@Composable
fun EpisodeContent(
    episode: ShowEpisodeModel,
    onCloseClicked: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color.Transparent,
                        Color.Black
                    )
                )
            )
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(context)
                .data(episode.image?.original)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_tv),
            contentDescription = episode.name.toString(),
            contentScale = ContentScale.Crop,
            alpha = 0.6f
        )
        IconButton(
            onClick = { onCloseClicked() }) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.Close,
                contentDescription = "Close Episode Detail",
                tint = LightBlueGreen
            )
        }
        Column(modifier = Modifier.align(Alignment.BottomStart)) {
            Row(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "S-" + episode.season.toString() + " |",
                    color = LightBlueGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = "E-" + episode.number.toString(),
                    modifier = Modifier.padding(start = 8.dp),
                    color = LightBlueGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = episode.name.toString(),
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                color = LightBlueGreen,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlueGreen)
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = stringResource(id = R.string.show_detail_summary),
                color = DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp).size(16.dp),
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Calendar Icon",
                    tint = DarkGray
                )
                Text(
                    text = episode.airdate.toString(),
                    color = DarkGray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = HtmlCompat.fromHtml(episode.summary.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
                .toString(),
            color = DarkGray,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
    }
}