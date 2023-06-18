package com.github.hirotask.listener

import com.github.hirotask.Main
import com.github.hirotask.event.PlayerAttackZombieEvent
import com.github.hirotask.event.ZombieDeathByPlayerEvent
import com.github.hirotask.infra.DAO
import com.github.syari.spigot.api.event.events

object MyEventListener {
    fun register() {
        Main.INSTANCE.events {
            event<PlayerAttackZombieEvent> {
                val player = it.player
                val zombie = it.zombie
            }
            event<ZombieDeathByPlayerEvent> {
                val player = it.player

//                player.playSound(Sound.ENTITY_ENDER_DRAGON_DEATH, volume = 0.5F)

                val dao = DAO()
                dao.insertPlayer(player)
                player.sendMessage("Kills: ${dao.getKills(player)}")
            }
        }
    }
}
