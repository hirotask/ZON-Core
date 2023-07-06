package com.github.hirotask.kills

import com.github.hirotask.core.usecase.AddStatusPointUseCase
import com.github.hirotask.core.usecase.AddZombieKillsUseCase
import com.github.hirotask.core.usecase.GetZONPlayerUseCase
import com.github.hirotask.core.usecase.InitZONPlayerUseCase
import com.github.hirotask.core.usecase.ReinforceStatusUseCase
import com.github.hirotask.kills.di.DaggerZONPlayerKillsComponent
import com.github.hirotask.kills.listener.EventListener
import com.github.hirotask.kills.listener.MyEventListener
import com.github.syari.spigot.api.EasySpigotAPIOption
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject

/**
 * メインクラス
 *
 */
class Main : JavaPlugin() {

    @Inject
    lateinit var addZombieKillsUseCase: AddZombieKillsUseCase

    @Inject
    lateinit var addStatusPointUseCase: AddStatusPointUseCase

    @Inject
    lateinit var initZONPlayerUseCase: InitZONPlayerUseCase

    @Inject
    lateinit var reinforceStatusUseCase: ReinforceStatusUseCase

    @Inject
    lateinit var getZonPlayerUseCase: GetZONPlayerUseCase

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
