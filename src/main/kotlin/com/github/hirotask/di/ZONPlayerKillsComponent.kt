package com.github.hirotask.di

import com.github.hirotask.mc.Main
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ProvidesModule::class, BindModule::class])
interface ZONPlayerKillsComponent {
    fun inject(main: Main)
}
