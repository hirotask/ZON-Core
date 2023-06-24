package com.github.hirotask.infra.zonplayer

import com.github.hirotask.domain.ZONPlayer
import org.bukkit.entity.Player

interface ZONPlayerRepository {

    fun getPlayerId(player: Player): Int

    fun updatePlayerName(zonPlayer: ZONPlayer, uuid: String): Int

    fun addZombieKills(playerId: Int, amount: Int): Int

    fun addStatusPoint(playerId: Int, amount: Int): Int

    fun getZombieKills(playerId: Int): Int

    fun getStatusPoint(playerId: Int): Int

    fun getZONPlayer(player: Player): ZONPlayer
}
