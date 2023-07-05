package com.github.hirotask.di

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.domain.ZONPlayerServiceImpl
import com.github.hirotask.usecase.AddStatusPointUseCase
import com.github.hirotask.usecase.AddZombieKillsUseCase
import com.github.hirotask.usecase.InitZONPlayerUseCase
import com.github.hirotask.usecase.impl.AddStatusPointUseCaseImpl
import com.github.hirotask.usecase.impl.AddZombieKillsUseCaseImpl
import com.github.hirotask.usecase.impl.InitZONPlayerUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * DaggerにおけるBindの設定を行うモジュール
 *
 * @suppress
 */
@Module
interface BindModule {
    @Binds
    @Singleton
    fun bindsZONPlayerService(impl: ZONPlayerServiceImpl): ZONPlayerService

    @Binds
    fun bindsAddStatusPointUseCase(impl : AddStatusPointUseCaseImpl) : AddStatusPointUseCase

    @Binds
    fun bindsAddZombieKillsUseCase(impl: AddZombieKillsUseCaseImpl): AddZombieKillsUseCase

    @Binds
    fun bindsInitZONPlayerUseCase(impl: InitZONPlayerUseCaseImpl): InitZONPlayerUseCase
}
