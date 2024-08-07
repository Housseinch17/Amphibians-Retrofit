package com.example.amphibiansappretrofitdata.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import com.example.amphibiansappretrofitdata.data.uistate.AmphibiansResponse
import com.example.amphibiansappretrofitdata.data.uistate.AmphibiansUiState
import com.example.amphibiansappretrofitdata.domain.usecase.GetAmphibiansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AmphibiansViewModel @Inject constructor(
    private val getAmphibiansUseCase: GetAmphibiansUseCase
): ViewModel() {
    private val _amphibians = MutableStateFlow(AmphibiansUiState())
    val amphibians = _amphibians.asStateFlow()

    init {
        getAmphibians()
    }

    private fun getAmphibians(){
        viewModelScope.launch {
            _amphibians.update {
                it.copy(
                    amphibiansResponse = try {
                        val amphibiansList = getAmphibiansUseCase.getAmphibians()
                        AmphibiansResponse.Success(amphibiansList)
                    } catch (e: IOException) {
                        AmphibiansResponse.Error
                    } catch (e: HttpException) {
                        AmphibiansResponse.Error
                    }
                )
            }
            }
        }
    }