package com.github.hirotask.di

import com.github.hirotask.infra.Database
import com.github.hirotask.infra.zonplayer.ZONPlayerRepository
import com.github.hirotask.infra.zonplayer.impl.ZONPlayerRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProvidesModule {
    @Provides
    @Singleton
    fun provideZONPlayerRepository(): ZONPlayerRepository {
        val database = Database()
        return ZONPlayerRepositoryImpl(database)
    }
}
