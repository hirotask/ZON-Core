package com.github.hirotask.core.di

import com.github.hirotask.core.domain.ZONPlayerService
import com.github.hirotask.core.domain.ZONPlayerServiceImpl
import com.github.hirotask.core.infra.zonplayer.ZONPlayerRepository
import com.github.hirotask.core.infra.zonplayer.ZONPlayerStatusRepository
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
