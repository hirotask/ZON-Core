package com.github.hirotask.di

import com.github.hirotask.mc.listener.EventListener
import com.github.hirotask.mc.listener.MyEventListener
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ProvidesModule::class, BindModule::class])
interface ZONPlayerKillsComponent {
    fun inject(eventListener: EventListener)
    fun inject(myEventListener: MyEventListener)
}
