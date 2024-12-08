package com.headbook.di

import com.headbook.player.data.PlayerRepositoryImpl
import com.headbook.player.domain.PlayerRepository
import com.headbook.player.presentation.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module {
    viewModelOf(::PlayerViewModel)
    singleOf(::PlayerRepositoryImpl).bind<PlayerRepository>()

}