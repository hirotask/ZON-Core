package com.github.hirotask.core.di

import com.github.hirotask.core.domain.services.ZONPlayerService
import com.github.hirotask.core.domain.services.ZONPlayerServiceImpl
import com.github.hirotask.core.domain.repository.ZONPlayerRepository
import com.github.hirotask.core.domain.repository.ZONPlayerStatusRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * DomainServiceのDIを設定するモジュール
 *
 * @suppress
 */
@Module
class DomainServiceModule {
    @Provides
    @Singleton
    fun provideZONPlayerService(
        zonPlayerRepository: ZONPlayerRepository,
        zonPlayerStatusRepository: ZONPlayerStatusRepository
    ): ZONPlayerService {
        return ZONPlayerServiceImpl(zonPlayerRepository, zonPlayerStatusRepository)
    }
}
