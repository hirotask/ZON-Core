package com.github.hirotask.mc.listener

import com.github.hirotask.di.DaggerZONPlayerKillsComponent
import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.mc.event.ZombieDeathByPlayerEvent
import com.github.syari.spigot.api.event.events
import com.github.syari.spigot.api.sound.playSound
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject

class MyEventListener(private val plugin: JavaPlugin) {

    @Inject
    lateinit var zonPlayerService: ZONPlayerService

    init {
        val zonplayerKillsComponent = DaggerZONPlayerKillsComponent.create()
        zonplayerKillsComponent.inject(this)
    }

    fun register() {
        plugin.events {
            event<ZombieDeathByPlayerEvent> {
                val player = it.player
                val zonPlayer = zonPlayerService.getZONPlayer(player)

                when (zonPlayerService.addZombieKills(zonPlayer)) {
                    50 -> {
                        player.sendMessage("50キル達成！")
                        player.inventory.addItem(ItemStack(Material.DIAMOND))
                        player.playSound(Sound.ENTITY_ENDER_DRAGON_DEATH, volume = 0.5F)
                    }
                    100 -> {
                        player.sendMessage("100キル達成！")
                        player.inventory.addItem(ItemStack(Material.NETHER_STAR))
                        player.playSound(Sound.ENTITY_ENDER_DRAGON_DEATH, volume = 0.5F)
                    }
                }
            }
        }
    }
}
