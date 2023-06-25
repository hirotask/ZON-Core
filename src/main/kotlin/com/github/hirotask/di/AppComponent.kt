package com.github.hirotask.di

import com.github.hirotask.domain.ZONPlayerService
import dagger.Component

@Component(modules = [DependencyModule::class])
interface AppComponent {
    fun inject(zonPlayerService: ZONPlayerService)
}
