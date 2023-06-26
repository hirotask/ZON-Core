package com.github.hirotask.infra.zonplayer

import com.github.hirotask.domain.ZONPlayer
import org.bukkit.entity.Player

interface ZONPlayerRepository {

    fun updatePlayerName(zonPlayer: ZONPlayer, uuid: String): Int

    fun addZombieKills(zonPlayer: ZONPlayer): Int

    fun addStatusPoint(zonPlayer: ZONPlayer, amount: Int): Int

    fun getZombieKills(zonPlayer: ZONPlayer): Int

    fun getStatusPoint(zonPlayer: ZONPlayer): Int

    fun getZONPlayer(player: Player): ZONPlayer

    fun addZONPlayer(player: Player): Int
}
