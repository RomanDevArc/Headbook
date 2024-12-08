package com.headbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.headbook.player.presentation.PlayerScreenRoot
import com.headbook.player.presentation.PlayerViewModel
import com.headbook.ui.theme.HeadbookTheme
import org.koin.androidx.compose.koinViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeadbookTheme {
                Scaffold (modifier = Modifier.padding(1.dp)){ innerPadding ->
                    val viewModel = koinViewModel<PlayerViewModel>()
                    PlayerScreenRoot(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel)
                }
            }
        }


    }
}

