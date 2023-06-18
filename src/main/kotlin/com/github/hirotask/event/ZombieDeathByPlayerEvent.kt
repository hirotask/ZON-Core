package com.github.hirotask.event

import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class ZombieDeathByPlayerEvent(val player: Player, val zombie: Zombie) : Event(){
    companion object {
        val handlers = HandlerList()
    }

    override fun getHandlers(): HandlerList {
        return PlayerAttackZombieEvent.handlers
    }
}