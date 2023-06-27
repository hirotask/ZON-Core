package com.github.hirotask.mc

import com.github.hirotask.mc.listener.EventListener
import com.github.hirotask.mc.listener.MyEventListener
import com.github.syari.spigot.api.EasySpigotAPIOption
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    companion object {
        const val PLUGIN_NAME = "ZON-Kills"
    }

    override fun onEnable() {
        // CustomInventoryの有効化
        EasySpigotAPIOption.useCustomInventory(this)

        // Eventの登録
        val eventListener = EventListener(this)
        val myEventListener = MyEventListener(this)
        eventListener.register()
        myEventListener.register()
        // Commandの登録
        val command = Command(this)
        command.register()
    }

    override fun onDisable() {
        super.onDisable()
    }
}
