package com.headbook.player.data

import android.app.Application
import android.net.Uri
import com.headbook.R
import com.headbook.player.domain.PlayerRepository
import com.headbook.player.presentation.getRawResourceUri
import com.headbook.player.presentation.mockedText

class PlayerRepositoryImpl(private val application: Application) : PlayerRepository {
    override suspend fun getAudioChaptersUri(): List<Uri> {
        return listOf(
            getRawResourceUri(application, R.raw.chapter_1),
            getRawResourceUri(application, R.raw.chapter_2),
            getRawResourceUri(application, R.raw.chapter_3),
            getRawResourceUri(application, R.raw.chapter_4),
        )
    }

    override suspend fun getTextOfChapters(): List<String> {
        return listOf(
            "Chapter 1 \n $mockedText",
            "Chapter 2 \n $mockedText",
            "Chapter 3 \n $mockedText",
            "Chapter 4 \n $mockedText"
        )
    }

}