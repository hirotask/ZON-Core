package com.github.hirotask.di

import com.github.hirotask.mc.Main
import dagger.Component
import javax.inject.Singleton
/**
 * DaggerにおけるField Injectionを行うためのComponent
 *
 * @suppress
 */
@Singleton
@Component(modules = [ProvidesModule::class, BindModule::class])
interface ZONPlayerKillsComponent {
    fun inject(main: Main)
}
