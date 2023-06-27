package com.github.hirotask.mc

import com.github.hirotask.mc.listener.EventListener
import com.github.hirotask.mc.listener.MyEventListener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    companion object {
        const val PLUGIN_NAME = "ZON-Kills"
    }

    override fun onEnable() {
        // Eventの登録
        val eventListener = EventListener(this)
        val myEventListener = MyEventListener(this)
        eventListener.register()
        myEventListener.register()
    }

    override fun onDisable() {
        super.onDisable()
    }
}