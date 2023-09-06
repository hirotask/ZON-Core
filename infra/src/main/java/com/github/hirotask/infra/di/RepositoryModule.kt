package com.github.hirotask.infra.di

import com.github.hirotask.core.domain.repository.ZONPlayerRepository
import com.github.hirotask.core.domain.repository.ZONPlayerStatusRepository
import com.github.hirotask.infra.Database
import com.github.hirotask.infra.zonplayer.impl.ZONPlayerRepositoryImpl
import com.github.hirotask.infra.zonplayer.impl.ZONPlayerStatusRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
/**
 * RepositoryのDIを設定するモジュール
 *
 * @suppress
 */
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideZONPlayerRepository(): ZONPlayerRepository {
        val database = Database()
        return ZONPlayerRepositoryImpl(database)
    }

    @Provides
    @Singleton
    fun provideZONPlayerStatusRepository(): ZONPlayerStatusRepository {
        val database = Database()
        return ZONPlayerStatusRepositoryImpl(database)
    }
}
