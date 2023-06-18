package com.github.hirotask

import com.github.hirotask.listener.EventListener
import com.github.hirotask.listener.MyEventListener
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
        EventListener.register()
        MyEventListener.register()
    }

    override fun onDisable() {
        super.onDisable()
    }
}
