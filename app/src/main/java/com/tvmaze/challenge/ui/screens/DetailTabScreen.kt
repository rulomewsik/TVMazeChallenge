package com.tvmaze.challenge.ui.screens

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.tvmaze.challenge.R
import com.tvmaze.challenge.remote.models.ScheduleModel
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.ui.theme.DarkGray
import com.tvmaze.challenge.ui.theme.LightBlueGreen
import com.tvmaze.challenge.ui.viewmodels.ShowDetailViewModel
import java.time.LocalDate

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailTabScreen(
    show: TVShowModel?,
    viewModel: ShowDetailViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlueGreen)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.show_detail_summary),
                color = DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = getShowDates(show),
                    color = DarkGray,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Text(
                    text = getShowGenres(show),
                    color = DarkGray,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = HtmlCompat.fromHtml(show?.summary.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
                .toString(),
            color = DarkGray,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )
        Divider(
            modifier = Modifier.padding(bottom = 16.dp),
            color = DarkGray,
            thickness = 1.dp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = "Clock Icon",
                tint = DarkGray
            )
            Text(
                text = getScheduleFormat(show?.schedule),
                color = DarkGray,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = R.drawable.ic_sand_clock),
                contentDescription = "Sand Clock Icon",
                tint = DarkGray
            )
            Text(
                text = getRuntimeFormat(show),
                color = DarkGray,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        }
    }
}

private fun getScheduleFormat(schedule: ScheduleModel?): String {
    val days = if (schedule?.days?.isNotEmpty() == true) schedule.days.joinToString() else "-"
    val time = if (schedule?.time?.isNotEmpty() == true) schedule.time else "--:--"
    return "$days | $time"
}

private fun getRuntimeFormat(show: TVShowModel?): String {
    return if (show?.runtime != null) {
        "${show.runtime} min"
    } else if (show?.averageRuntime != null) {
        "${show.averageRuntime} min"
    } else {
        "-"
    }
}

private fun getShowDates(show: TVShowModel?): String {
    val premieredYear = if (show?.premiered != null) {
        val premieredDate = LocalDate.parse(show.premiered)
        premieredDate.year.toString()
    } else {
        "-"
    }

    val endedYear = if (show?.ended != null) {
        val endedDate = LocalDate.parse(show.ended)
        endedDate.year.toString()
    } else {
        "now"
    }

    return "$premieredYear - $endedYear"
}

private fun getShowGenres(show: TVShowModel?): String =
    if (show?.genres?.isEmpty() == false) show.genres.joinToString(separator = " • ") else "-"

@Composable
fun HtmlSummary(text: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
        }
    })
}