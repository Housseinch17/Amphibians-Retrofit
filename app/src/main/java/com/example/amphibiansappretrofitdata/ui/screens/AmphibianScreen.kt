package com.example.amphibiansappretrofitdata.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansappretrofitdata.R
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import com.example.amphibiansappretrofitdata.data.uistate.AmphibiansResponse


@Composable
fun AmphibianScreen(modifier: Modifier, amphibiansResponse: AmphibiansResponse) {
    when (amphibiansResponse) {
        is AmphibiansResponse.Success -> ResultsScreen(
            modifier = modifier,
            amphibianList = amphibiansResponse.list
        )

        is AmphibiansResponse.Error -> ErrorScreen(modifier = modifier)
        is AmphibiansResponse.Loading -> LoadingScreen(modifier = modifier)
    }
}

@Composable
fun ResultsScreen(modifier: Modifier, amphibianList: List<AmphibiansItem>) {
    AmphibianList(modifier = modifier.padding(horizontal = 20.dp), amphibianList)
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.loading),
        contentDescription = stringResource(id = R.string.loading),
        contentScale = ContentScale.Crop,
        modifier = modifier.size(200.dp)
    )
}


@Composable
fun ErrorScreen(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.connectionerror),
            contentDescription = stringResource(
                id = R.string.failed_to_load
            )
        )
        Text(
            text = stringResource(id = R.string.failed_to_load),
            modifier = Modifier,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AmphibianList(modifier: Modifier, amphibianList: List<AmphibiansItem>) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(bottom = 20.dp)
    ) {
        items(amphibianList) { amphian ->
            AmphibianItem(modifier = Modifier.fillMaxWidth(), amphibiansItem = amphian)
        }
    }
}

@Composable
fun AmphibianItem(modifier: Modifier, amphibiansItem: AmphibiansItem) {
    Column(
        modifier = modifier
            .border(
                border = BorderStroke(width = 1.dp, color = Color.Blue),
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
            )
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            .background(Color.LightGray)
    ) {
        Text(
            text = amphibiansItem.name + " (${amphibiansItem.type})",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        AmphibiansImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), imageSrc = amphibiansItem.image
        )
        Text(
            text = amphibiansItem.description, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal)
        )
    }
}

@Composable
fun AmphibiansImage(modifier: Modifier, imageSrc: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageSrc)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}