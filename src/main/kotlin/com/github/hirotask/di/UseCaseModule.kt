package com.github.hirotask.di

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.usecase.AddStatusPointUseCase
import com.github.hirotask.usecase.AddZombieKillsUseCase
import com.github.hirotask.usecase.GetZONPlayerUseCase
import com.github.hirotask.usecase.InitZONPlayerUseCase
import com.github.hirotask.usecase.ReinforceStatusUseCase
import com.github.hirotask.usecase.impl.AddStatusPointUseCaseImpl
import com.github.hirotask.usecase.impl.AddZombieKillsUseCaseImpl
import com.github.hirotask.usecase.impl.GetZONPlayerUseCaseImpl
import com.github.hirotask.usecase.impl.InitZONPlayerUseCaseImpl
import com.github.hirotask.usecase.impl.ReinforceStatusUseCaseImpl
import dagger.Module
import dagger.Provides

/**
 * UseCaseのDIを設定するモジュール
 *
 * @suppress
 */
@Module
class UseCaseModule {

    @Provides
    fun provideGetZONPlayerUseCase(zonPlayerService: ZONPlayerService): GetZONPlayerUseCase {
        return GetZONPlayerUseCaseImpl(zonPlayerService)
    }

    @Provides
    fun provideAddStatusPointUseCase(
        zonPlayerService: ZONPlayerService,
        getZonPlayerUseCase: GetZONPlayerUseCase
    ): AddStatusPointUseCase {
        return AddStatusPointUseCaseImpl(zonPlayerService, getZonPlayerUseCase)
    }

    @Provides
    fun provideAddZombieKillsUseCase(
        zonPlayerService: ZONPlayerService,
        getZonPlayerUseCase: GetZONPlayerUseCase
    ): AddZombieKillsUseCase {
        return AddZombieKillsUseCaseImpl(zonPlayerService, getZonPlayerUseCase)
    }

    @Provides
    fun provideInitZONPlayerUseCase(zonPlayerService: ZONPlayerService): InitZONPlayerUseCase {
        return InitZONPlayerUseCaseImpl(zonPlayerService)
    }

    @Provides
    fun provideReinforceStatusUseCase(
        zonPlayerService: ZONPlayerService,
        getZonPlayerUseCase: GetZONPlayerUseCase
    ): ReinforceStatusUseCase {
        return ReinforceStatusUseCaseImpl(zonPlayerService, getZonPlayerUseCase)
    }
}
