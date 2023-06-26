package com.github.hirotask.di

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.domain.ZONPlayerServiceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BindModule {
    @Binds
    @Singleton
    fun bindsZONPlayerService(impl: ZONPlayerServiceImpl): ZONPlayerService
}