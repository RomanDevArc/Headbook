package com.headbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.headbook.player.presentation.PlayerScreenRoot
import com.headbook.player.presentation.PlayerViewModel
import com.headbook.ui.theme.HeadbookTheme
import org.koin.androidx.compose.koinViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HeadbookTheme {
                val viewModel = koinViewModel<PlayerViewModel>()
                PlayerScreenRoot(viewModel)
            }
        }
    }
}

