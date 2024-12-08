package com.headbook.player.presentation.components


data class AudioPlayerState(
    val isPlaying: Boolean = false,
    val duration: Long = 0L,
    val currentPosition: Long = 0L,
    val currentAudioNumber: Int = 0,
    val audioCount: Int = 0,
    val speed: Float = 1.0f,
    val showText: Boolean = false,
    val sliderValue: Float = currentPosition/duration.toFloat(),
    val chapterText: String = "",
)

