package com.example.amphibiansappretrofitdata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.amphibiansappretrofitdata.ui.screens.AmphibianScreen
import com.example.amphibiansappretrofitdata.ui.theme.AmphibiansAppRetrofitDataTheme
import com.example.amphibiansappretrofitdata.ui.viewmodels.AmphibiansViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmphibiansAppRetrofitDataTheme {
                val amphibiansViewModel: AmphibiansViewModel = hiltViewModel()
                val amphibiansUiState by amphibiansViewModel.amphibians.collectAsStateWithLifecycle()
                Scaffold(
                    topBar = {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically){
                            Text(text = stringResource(id = R.string.app_name), modifier = Modifier
                                .weight(1f)
                                .padding(end = 10.dp),
                                style = MaterialTheme.typography.titleLarge)
                            IconButton(onClick = amphibiansViewModel::updateAmphibians, modifier = Modifier.size(40.dp)) {
                                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = stringResource(
                                    id = R.string.update
                                ))
                            }
                        }

                    }
                ){ innerPadding->
                    AmphibianScreen(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding), amphibiansResponse = amphibiansUiState.amphibiansResponse)
                }
            }
        }
    }
}
