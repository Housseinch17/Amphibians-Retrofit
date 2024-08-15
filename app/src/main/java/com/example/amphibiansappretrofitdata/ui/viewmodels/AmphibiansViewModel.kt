package com.example.amphibiansappretrofitdata.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import com.example.amphibiansappretrofitdata.data.uistate.AmphibiansUiState
import com.example.amphibiansappretrofitdata.domain.usecase.GetAmphibiansUseCase
import com.example.amphibiansappretrofitdata.domain.usecase.UpdateAmphibiansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmphibiansViewModel @Inject constructor(
    private val getAmphibiansUseCase: GetAmphibiansUseCase,
    private val updateAmphibiansUseCase: UpdateAmphibiansUseCase,
) : ViewModel() {
    private val _amphibians = MutableStateFlow(AmphibiansUiState())
    val amphibians = _amphibians.asStateFlow()

    init {
        getAmphibians()
    }

    fun updateAmphibians() {
        viewModelScope.launch {
            _amphibians.update {
                it.copy(
                    amphibiansResponse = fetchAmphibians { updateAmphibiansUseCase.updateAmphibians() }
                )
            }
        }
    }

    private fun getAmphibians() {
        viewModelScope.launch {
            _amphibians.update {
                it.copy(
                    amphibiansResponse = fetchAmphibians { getAmphibiansUseCase.getAmphibians() }
                )
            }
        }
    }

    private suspend fun fetchAmphibians(fetch: suspend () -> List<AmphibiansItem>): AmphibiansResponse {
        val amphibiansList = fetch()
        val amphibiansResponse = if (amphibiansList.isNotEmpty()) {
            AmphibiansResponse.Success(amphibiansList)
        } else {
            AmphibiansResponse.Error
        }
        return amphibiansResponse
    }
}

sealed interface AmphibiansResponse {
    data class Success(val list: List<AmphibiansItem>) : AmphibiansResponse
    data object Error : AmphibiansResponse
    data object Loading : AmphibiansResponse
}
