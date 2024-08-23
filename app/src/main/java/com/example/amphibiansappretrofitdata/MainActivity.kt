package com.example.amphibiansappretrofitdata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.amphibiansappretrofitdata.data.DataSource
import com.example.amphibiansappretrofitdata.ui.navigation.Navigation
import com.example.amphibiansappretrofitdata.ui.theme.AmphibiansAppRetrofitDataTheme
import com.example.amphibiansappretrofitdata.ui.theme.itemTitle
import com.example.amphibiansappretrofitdata.ui.viewmodels.AmphibiansViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmphibiansAppRetrofitDataTheme {

                val navHostController = rememberNavController()
                // Get current back stack entry
                val backStackEntry by navHostController.currentBackStackEntryAsState()

                // Retrieve the current route from the back stack entry, or default to the Amphibians screen if null
                val route =
                    backStackEntry?.destination?.route ?: DataSource.AmphibianScreen.Amphibians.name

                // Determine the current screen based on the route
                val currentScreen = when {
                    // Check if the route starts with the base name for the AmphibianDetail screen.
                    // This handles routes with dynamic parameters like "AmphibianDetail/123" or "AmphibianDetail/abc".
                    route.startsWith(DataSource.AmphibianScreen.AmphibianDetail.name) -> DataSource.AmphibianScreen.AmphibianDetail

                    // Check if the route exactly matches the name for the Amphibians screen.
                    route == DataSource.AmphibianScreen.Amphibians.name -> DataSource.AmphibianScreen.Amphibians

                    // Add more cases if there are additional screens with different static routes
                    // For example:
                    // route == DataSource.AmphibianScreen.OtherScreen.name -> DataSource.AmphibianScreen.OtherScreen

                    // Default case: if none of the specific routes match, fall back to the default screen
                    else -> DataSource.AmphibianScreen.Amphibians
                }
                val amphibiansViewModel = hiltViewModel<AmphibiansViewModel>()

                Scaffold(topBar = {
                    AppBar(
                        modifier = Modifier.fillMaxWidth(),
                        amphibianScreen = currentScreen,
                        canNavigateBack = navHostController.previousBackStackEntry != null,
                        canUpdate = currentScreen == DataSource.AmphibianScreen.Amphibians,
                        amphibiansViewModel = amphibiansViewModel
                    ) {
                        navHostController.navigateUp()
                    }
                }) { innerPadding ->

                    Navigation(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        navHostController = navHostController,
                        amphibiansViewModel = amphibiansViewModel,
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier,
    amphibianScreen: DataSource.AmphibianScreen,
    canNavigateBack: Boolean,
    canUpdate: Boolean,
    amphibiansViewModel: AmphibiansViewModel,
    navigateBack: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = stringResource(id = amphibianScreen.title),
            modifier = Modifier.fillMaxWidth(1f),
            textAlign = TextAlign.Center,
            style = itemTitle.copy(fontSize = 24.sp),
        )
    }, navigationIcon = {
        if (canNavigateBack) {
            IconButton(
                onClick = navigateBack, modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(
                        id = R.string.back
                    )
                )
            }
        }
    }, actions = {
        if (canUpdate) {
            IconButton(
                onClick = amphibiansViewModel::updateAmphibians
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh, contentDescription = stringResource(
                        id = R.string.update
                    )
                )
            }
        }
    }, modifier = modifier
    )
}
