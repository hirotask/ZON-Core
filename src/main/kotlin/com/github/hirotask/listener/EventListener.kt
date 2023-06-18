package com.github.hirotask.listener

import com.github.hirotask.Main
import com.github.hirotask.event.PlayerAttackZombieEvent
import com.github.hirotask.event.ZombieDeathByPlayerEvent
import com.github.syari.spigot.api.event.events
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.entity.EntityDamageByEntityEvent

object EventListener {
    fun register() {
        Main.INSTANCE.events {
            event<EntityDamageByEntityEvent> {
                if (it.damager !is Player) return@event
                if (it.entity !is Zombie) return@event

                val player = it.damager as Player
                val zombie = it.entity as Zombie

                if (zombie.health <= 0) {
                    Main.INSTANCE.server.pluginManager.callEvent(ZombieDeathByPlayerEvent(player, zombie))
                } else {
                    Main.INSTANCE.server.pluginManager.callEvent(PlayerAttackZombieEvent(player, zombie))
                }
            }
        }
    }
}
