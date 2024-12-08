package com.headbook.player.presentation

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.headbook.player.domain.PlayerRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield


class PlayerViewModel(application: Application, playerRepository: PlayerRepository) :
    AndroidViewModel(application) {

    private val exoPlayer: ExoPlayer = ExoPlayer.Builder(application).build()
    private val playSpeedValuesList = listOf(0.5f, 0.75f, 1f, 1.25f, 1.5f, 1.75f, 2f)
    private var currentSpeedIndex = 2
    private var currentAudioIndex = 0
    private lateinit var audioUriList: List<Uri>
    private lateinit var chapterTextList: List<String>
    private val _playerState = MutableStateFlow(AudioPlayerState())
    val playerState = _playerState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                audioUriList = async { playerRepository.getAudioChaptersUri() }.await()
                chapterTextList = async { playerRepository.getTextOfChapters() }.await()
                updatePlayerTrack(audioUriList[currentAudioIndex])
                observePlayerState()
            } catch (e: Exception) {
                yield()
                e.printStackTrace()
            }
        }
    }


    private suspend fun observePlayerState() {
        while (true) {
            _playerState.value = _playerState.value.copy(
                isPlaying = exoPlayer.isPlaying,
                duration = exoPlayer.duration.coerceAtLeast(0L),
                currentPosition = exoPlayer.currentPosition,
                currentAudioNumber = currentAudioIndex + 1,
                audioCount = audioUriList.size,
                speed = playSpeedValuesList[currentSpeedIndex],
                chapterText = chapterTextList[currentAudioIndex]
            )
            kotlinx.coroutines.delay(100)
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

