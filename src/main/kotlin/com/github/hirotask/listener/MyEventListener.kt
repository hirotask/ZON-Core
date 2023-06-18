package com.github.hirotask.listener

import com.github.hirotask.Main
import com.github.hirotask.event.PlayerAttackZombieEvent
import com.github.hirotask.event.ZombieDeathByPlayerEvent
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
                val zombie = it.zombie
            }
        }
    }
}