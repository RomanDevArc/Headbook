package com.headbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.headbook.player.presentation.PlayerScreenRoot
import com.headbook.ui.theme.HeadbookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HeadbookTheme {
                PlayerScreenRoot()
            }
        }
    }
}
