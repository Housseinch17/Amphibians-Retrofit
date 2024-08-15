package com.example.amphibiansappretrofitdata.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibiansappretrofitdata.data.uistate.AmphibianDetailUiState
import com.example.amphibiansappretrofitdata.domain.usecase.GetAmphibiansByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmphibianDetailViewModel @Inject constructor(
    private val getAmphibiansByNameUseCase: GetAmphibiansByNameUseCase
) : ViewModel() {
    private val _amphibianDetail = MutableStateFlow(AmphibianDetailUiState())
    val amphibianDetail = _amphibianDetail.asStateFlow()


    suspend fun getAmphibiansByName(name: String) {
        viewModelScope.launch {
            _amphibianDetail.update {
                it.copy(
                    amphibiansItem = getAmphibiansByNameUseCase.getAmphibiansByName(name)
                )
            }
        }
    }
}