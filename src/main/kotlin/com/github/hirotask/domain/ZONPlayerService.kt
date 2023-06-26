package com.github.hirotask.domain

import org.bukkit.entity.Player

interface ZONPlayerService {

    fun addZONPlayer(player: Player): Int

    fun getZONPlayer(player: Player): ZONPlayer

    fun addZombieKills(zonPlayer: ZONPlayer): Int

    fun addStatusPoint(zonPlayer: ZONPlayer, amount: Int): Int
}
