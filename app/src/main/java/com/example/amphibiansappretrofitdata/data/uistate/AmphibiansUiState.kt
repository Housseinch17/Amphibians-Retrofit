package com.example.amphibiansappretrofitdata.data.uistate

import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem

data class AmphibiansUiState(
    val amphibiansResponse: AmphibiansResponse = AmphibiansResponse.Loading
)

sealed interface AmphibiansResponse{
    data class Success(val list: List<AmphibiansItem>) : AmphibiansResponse
    data object Error : AmphibiansResponse
    data object Loading : AmphibiansResponse
}
