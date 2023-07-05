package com.github.hirotask.di

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
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindsZONPlayerActionUseCase(impl: GetZONPlayerUseCaseImpl): GetZONPlayerUseCase

    @Binds
    fun bindsAddStatusPointUseCase(impl: AddStatusPointUseCaseImpl): AddStatusPointUseCase

    @Binds
    fun bindsAddZombieKillsUseCase(impl: AddZombieKillsUseCaseImpl): AddZombieKillsUseCase

    @Binds
    fun bindsInitZONPlayerUseCase(impl: InitZONPlayerUseCaseImpl): InitZONPlayerUseCase

    @Binds
    fun bindsReinforceStatusUseCase(impl: ReinforceStatusUseCaseImpl): ReinforceStatusUseCase
}
