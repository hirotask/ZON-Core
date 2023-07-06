package com.github.hirotask.mc.di

import com.github.hirotask.core.di.DomainServiceModule
import com.github.hirotask.core.di.RepositoryModule
import com.github.hirotask.core.di.UseCaseModule
import com.github.hirotask.mc.Main
import dagger.Component
import javax.inject.Singleton
/**
 * DaggerにおけるField Injectionを行うためのComponent
 *
 * @suppress
 */
@Singleton
@Component(modules = [DomainServiceModule::class, RepositoryModule::class, UseCaseModule::class])
interface ZONPlayerKillsComponent {
    fun inject(main: Main)
}
