package com.github.hirotask.mc.listener

import com.github.hirotask.Main
import com.github.hirotask.di.DaggerZONPlayerKillsComponent
import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.mc.event.PlayerAttackZombieEvent
import com.github.hirotask.mc.event.ZombieDeathByPlayerEvent
import com.github.syari.spigot.api.event.events
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import javax.inject.Inject

class EventListener {

    @Inject
    lateinit var zonPlayerService: ZONPlayerService

    init {
        val zonplayerKillsComponent = DaggerZONPlayerKillsComponent.create()
        zonplayerKillsComponent.inject(this)
    }

    fun register() {
        Main.INSTANCE.events {
            event<EntityDamageByEntityEvent> {
                if (it.damager !is Player) return@event
                if (it.entity !is Zombie) return@event

                val player = it.damager as Player
                val zombie = it.entity as Zombie

                if (zombie.health - it.damage <= 0) {
                    Main.INSTANCE.server.pluginManager.callEvent(ZombieDeathByPlayerEvent(player, zombie))
                } else {
                    Main.INSTANCE.server.pluginManager.callEvent(PlayerAttackZombieEvent(player, zombie))
                }
            }
            event<PlayerJoinEvent> {
                val player = it.player
                val zonPlayer = zonPlayerService.getZONPlayer(player)
                if (zonPlayer.zombieKillCount == -1 && zonPlayer.statusPoint == -1) {
                    zonPlayerService.addZONPlayer(player)
                }
            }
        }
    }
}
