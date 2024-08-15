package com.example.amphibiansappretrofitdata.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.amphibiansappretrofitdata.data.DataSource
import com.example.amphibiansappretrofitdata.ui.screens.AmphibianDetail
import com.example.amphibiansappretrofitdata.ui.screens.AmphibianScreen
import com.example.amphibiansappretrofitdata.ui.theme.itemTitle
import com.example.amphibiansappretrofitdata.ui.viewmodels.AmphibianDetailViewModel
import com.example.amphibiansappretrofitdata.ui.viewmodels.AmphibiansViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Navigation(
    modifier: Modifier,
    navHostController: NavHostController,
    amphibiansViewModel: AmphibiansViewModel,
    amphibianDetailViewModel: AmphibianDetailViewModel
) {
    val amphibianUiState by amphibiansViewModel.amphibians.collectAsStateWithLifecycle()
    val amphibianDetailUiState by amphibianDetailViewModel.amphibianDetail.collectAsStateWithLifecycle()

    NavHost(
        navController = navHostController,
        startDestination = DataSource.AmphibianScreen.Amphibians.name
    ) {
        composable(route = DataSource.AmphibianScreen.Amphibians.name) {
            AmphibianScreen(
                modifier = modifier.padding(horizontal = 20.dp),
                amphibiansResponse = amphibianUiState.amphibiansResponse
            ) { name ->
                navHostController.navigate(DataSource.AmphibianScreen.AmphibianDetail.name + "/$name")
            }
        }
        composable(
            route = DataSource.AmphibianScreen.AmphibianDetail.name + "/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        )
        { backStackEntry ->
            val scope = rememberCoroutineScope()
            val name = backStackEntry.arguments?.getString("name")
            scope.launch {
                if (name != null) {
                    amphibianDetailViewModel.getAmphibiansByName(name)
                }
            }
            amphibianDetailUiState.amphibiansItem?.let {
                AmphibianDetail(
                    modifier = modifier.wrapContentHeight().padding(horizontal = 20.dp),
                    amphibiansItem = it
                )
            } ?: Text(
                text = "No Amphibian is found",
                modifier = Modifier.fillMaxSize().padding(bottom = 20.dp),
                style = itemTitle
            )
        }
    }
}