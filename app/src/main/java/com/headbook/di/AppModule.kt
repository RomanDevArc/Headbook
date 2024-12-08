package com.headbook.di

import com.headbook.player.presentation.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    viewModelOf(::PlayerViewModel)
}