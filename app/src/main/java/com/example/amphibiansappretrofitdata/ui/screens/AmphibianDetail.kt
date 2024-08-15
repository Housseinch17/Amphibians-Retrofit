package com.example.amphibiansappretrofitdata.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem

@Composable
fun AmphibianDetail(
    modifier: Modifier, amphibiansItem: AmphibiansItem,
) {
    AmphibianItem(
        modifier = modifier,
        amphibiansItem = amphibiansItem,
        onClick = {}
    )
}
