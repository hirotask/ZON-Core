package com.github.hirotask.kills.listener

import com.github.hirotask.kills.Main
import com.github.hirotask.kills.event.PlayerAttackZombieEvent
import com.github.hirotask.kills.event.ZombieDeathByPlayerEvent
import com.github.syari.spigot.api.event.events
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent

/**
 * SpigotAPIに実装されているイベントに対するリスナー
 *
 */
object EventListener {

    fun register(main: Main) {
        main.events {
            event<EntityDamageByEntityEvent> {
                if (it.damager !is Player) return@event
                if (it.entity !is Zombie) return@event

                val player = it.damager as Player
                val zombie = it.entity as Zombie

                if (zombie.health - it.damage <= 0) {
                    main.server.pluginManager.callEvent(ZombieDeathByPlayerEvent(player, zombie))
                } else {
                    main.server.pluginManager.callEvent(PlayerAttackZombieEvent(player, zombie))
                }
            }
            event<PlayerJoinEvent> {
                val player = it.player
                main.initZONPlayerUseCase.invoke(player.name, player.uniqueId.toString())
            }
        }
    }
}
