package com.github.hirotask.di

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.domain.ZONPlayerServiceImpl
import com.github.hirotask.infra.Database
import com.github.hirotask.infra.zonplayer.ZONPlayerRepository
import com.github.hirotask.infra.zonplayer.impl.ZONPlayerRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DependencyModule {
    @Provides
    @Singleton
    fun provideZONPlayerRepository(): ZONPlayerRepository {
        return ZONPlayerRepositoryImpl(Database())
    }

    @Provides
    fun provideZONPlayerService(zonPlayerRepository: ZONPlayerRepository): ZONPlayerService {
        return ZONPlayerServiceImpl(zonPlayerRepository)
    }
}
