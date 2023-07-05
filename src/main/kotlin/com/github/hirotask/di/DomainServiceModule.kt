package com.github.hirotask.di

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.domain.ZONPlayerServiceImpl
import com.github.hirotask.infra.zonplayer.ZONPlayerRepository
import com.github.hirotask.infra.zonplayer.ZONPlayerStatusRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * DaggerにおけるBindの設定を行うモジュール
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
