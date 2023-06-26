package com.github.hirotask.domain

import com.github.hirotask.infra.zonplayer.ZONPlayerRepository
import org.bukkit.entity.Player
import javax.inject.Inject

class ZONPlayerServiceImpl @Inject constructor(private val zonPlayerRepository: ZONPlayerRepository) : ZONPlayerService {
    override fun addZONPlayer(player: Player): Int {
        return zonPlayerRepository.addZONPlayer(player)
    }

    override fun getZONPlayer(player: Player): ZONPlayer {
        return zonPlayerRepository.getZONPlayer(player)
    }

    override fun addZombieKills(zonPlayer: ZONPlayer): Int {
        zonPlayerRepository.addZombieKills(zonPlayer)
        zonPlayer.zombieKillCount = zonPlayerRepository.getZombieKills(zonPlayer)
        return zonPlayer.zombieKillCount
    }

    override fun addStatusPoint(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayerRepository.addStatusPoint(zonPlayer, amount)
        zonPlayer.statusPoint = zonPlayerRepository.getStatusPoint(zonPlayer)
        return zonPlayer.statusPoint
    }
}
