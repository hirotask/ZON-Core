package com.github.hirotask.mc.listener

import com.github.hirotask.di.DaggerZONPlayerKillsComponent
import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.exc.ZONPlayerNotFoundException
import com.github.hirotask.mc.Main
import com.github.hirotask.mc.event.PlayerAttackZombieEvent
import com.github.hirotask.mc.event.ZombieDeathByPlayerEvent
import com.github.syari.spigot.api.event.events
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject

class EventListener(private val plugin : JavaPlugin) {

    @Inject
    lateinit var zonPlayerService: ZONPlayerService

    init {
        val zonplayerKillsComponent = DaggerZONPlayerKillsComponent.create()
        zonplayerKillsComponent.inject(this)
    }

    fun register() {
        plugin.events {
            event<EntityDamageByEntityEvent> {
                if (it.damager !is Player) return@event
                if (it.entity !is Zombie) return@event

                val player = it.damager as Player
                val zombie = it.entity as Zombie

                if (zombie.health - it.damage <= 0) {
                    plugin.server.pluginManager.callEvent(ZombieDeathByPlayerEvent(player, zombie))
                } else {
                    plugin.server.pluginManager.callEvent(PlayerAttackZombieEvent(player, zombie))
                }
            }
            event<PlayerJoinEvent> {
                val player = it.player
                try {
                    zonPlayerService.getZONPlayer(player)
                } catch (e: ZONPlayerNotFoundException) {
                    zonPlayerService.addZONPlayer(player)
                }
            }
        }
    }
}
