package com.github.hirotask.mc

import com.github.hirotask.di.DaggerZONPlayerKillsComponent
import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.mc.listener.EventListener
import com.github.hirotask.mc.listener.MyEventListener
import com.github.syari.spigot.api.EasySpigotAPIOption
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject

/**
 * メインクラス
 *
 */
class Main : JavaPlugin() {

    @Inject
    lateinit var zonPlayerService: ZONPlayerService

    init {
        val zonplayerKillsComponent = DaggerZONPlayerKillsComponent.create()
        zonplayerKillsComponent.inject(this)
    }

    override fun onEnable() {
        // CustomInventoryの有効化
        EasySpigotAPIOption.useCustomInventory(this)

        // Eventの登録
        EventListener.register(this)
        MyEventListener.register(this)
        // Commandの登録
        val command = Command(this)
        command.register()
    }
}
