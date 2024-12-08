package com.headbook.player.presentation.components

import androidx.compose.runtime.*

@Composable
fun AudioPlayerWithScroller(

) {
//    val context = LocalContext.current
//
//
//
//    // Initialize ExoPlayer for audio playback
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            setMediaItem(MediaItem.fromUri(uri))
//            prepare()
//            playWhenReady = true
//        }
//    }
//
//    // State variables for audio duration and playback position
//    var audioDuration by remember { mutableStateOf(0L) }
//    var currentPosition by remember { mutableStateOf(0L) }
//
//    // Update audio duration and position periodically
//    LaunchedEffect(exoPlayer) {
//        delay(2000)
//        audioDuration = exoPlayer.duration
//        while (true) {
//            currentPosition = exoPlayer.currentPosition
//            delay(500L) // Refresh every 500ms
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Spacer(modifier = Modifier.height(16.dp))
//
//        val calculatedValue = currentPosition.toFloat() / audioDuration.toFloat()
//        // Slider for controlling audio playback
//        Slider(
//            value =
//                if (calculatedValue.isNaN()) {
//                    0f
//                } else
//                    calculatedValue
//            ,
//            onValueChange = { value ->
//                val newPosition = (value * audioDuration).toLong()
//                exoPlayer.seekTo(newPosition)
//                currentPosition = newPosition
//            },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Display current and total audio time
//        Text(
//            text = "Current: ${formatTime(currentPosition)} / Total: ${formatTime(audioDuration)}",
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//    }
}

// Utility function to format time in mm:ss
fun formatTime(timeMs: Long): String {
    val minutes = (timeMs / 1000) / 60
    val seconds = (timeMs / 1000) % 60
    return String.format("%02d:%02d", minutes, seconds)
}
