package com.github.hirotask.kills.event

import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * プレイヤーがゾンビを攻撃した時に発火するイベント
 *
 * @property player
 * @property zombie
 */
class PlayerAttackZombieEvent(val player: Player, val zombie: Zombie) : Event(), Cancellable {
    var cancel: Boolean = false

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

    override fun isCancelled(): Boolean {
        return cancel
    }

    override fun setCancelled(p0: Boolean) {
        cancel = p0
    }

    companion object {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }
}
