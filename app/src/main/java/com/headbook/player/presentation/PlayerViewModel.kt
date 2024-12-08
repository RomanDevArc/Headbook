package com.headbook.player.presentation

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.headbook.R
import com.headbook.player.presentation.components.AudioPlayerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val exoPlayer: ExoPlayer = ExoPlayer.Builder(application).build()
    private val _playerState = MutableStateFlow(AudioPlayerState())
    val playerState = _playerState.asStateFlow()

    val audioUriList = listOf(
        getRawResourceUri(application, R.raw.chapter_1),
        getRawResourceUri(application, R.raw.chapter_2),
        getRawResourceUri(application, R.raw.chapter_3),
        getRawResourceUri(application, R.raw.chapter_4),
    )

    val playSpeedValuesList = listOf(0.5f, 0.75f, 1f, 1.25f, 1.5f, 1.75f, 2f)
    var currentSpeedIndex = 2
    var currentAudioIndex = 0

    val textList = listOf(
        "Chapter 1 \n $mockedText",
        "Chapter 2 \n $mockedText",
        "Chapter 3 \n $mockedText",
        "Chapter 4 \n $mockedText"
    )


    init {
        updatePlayerTrack(audioUriList[currentAudioIndex])
        observePlayerState()
    }


    private fun observePlayerState() {
        viewModelScope.launch {
            while (true) {
                _playerState.value = _playerState.value.copy(
                    isPlaying = exoPlayer.isPlaying,
                    duration = exoPlayer.duration.coerceAtLeast(0L),
                    currentPosition = exoPlayer.currentPosition,
                    currentAudioNumber = currentAudioIndex + 1,
                    audioCount = audioUriList.size,
                    speed = playSpeedValuesList[currentSpeedIndex],
                    chapterText = textList[currentAudioIndex]
                )
                kotlinx.coroutines.delay(500) // Update every 500ms
            }
        }
    }

    fun playPause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
    }

    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }

    fun sliderValueChanged(value: Float) {
        val newPosition = (value * _playerState.value.duration).toLong()
        exoPlayer.seekTo(newPosition)
    }

    fun onPreviousClicked() {
        if (currentAudioIndex != 0) {
            currentAudioIndex--
            updatePlayerTrack(audioUriList[currentAudioIndex])
        }
    }

    fun onNextClicked() {
        if (currentAudioIndex < audioUriList.size - 1) {
            currentAudioIndex++
            updatePlayerTrack(audioUriList[currentAudioIndex])

        }
    }

    fun updatePlayerTrack(audioUri: Uri) {
        exoPlayer.setMediaItem(MediaItem.fromUri(audioUri))
        exoPlayer.prepare()
    }

    fun onSpeedClicked() {
        if (currentSpeedIndex == playSpeedValuesList.size - 1)
            currentSpeedIndex = 0
        else
            currentSpeedIndex++
        exoPlayer.setPlaybackSpeed(playSpeedValuesList[currentSpeedIndex])
    }

    fun onTextSwitcherClicked(show: Boolean) {
        _playerState.value = _playerState.value.copy(
            showText = show
        )
    }
}

