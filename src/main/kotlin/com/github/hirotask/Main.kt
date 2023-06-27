package com.github.hirotask

import com.github.hirotask.mc.listener.EventListener
import com.github.hirotask.mc.listener.MyEventListener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    companion object {
        internal lateinit var INSTANCE: Main

        const val PLUGIN_NAME = "ZON-Kills"
    }

    init {
        INSTANCE = this
    }

    override fun onEnable() {
        // Eventの登録
        val eventListener = EventListener()
        val myEventListener = MyEventListener()
        eventListener.register()
        myEventListener.register()
    }

    override fun onDisable() {
        super.onDisable()
    }
}
