package com.headbook.player.domain

import android.net.Uri

interface PlayerRepository {
    suspend fun getAudioChaptersUri(): List<Uri>
    suspend fun getTextOfChapters(): List<String>

}