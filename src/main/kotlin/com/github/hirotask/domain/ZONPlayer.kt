package com.github.hirotask.domain

import com.github.hirotask.infra.zonplayer.ZONPlayerRepository
import org.bukkit.entity.Player
import javax.inject.Inject

class ZONPlayer(val player: Player, var zombieKillCount: Int, var statusPoint: Int) {
    @Inject
    lateinit var zonPlayerRepository: ZONPlayerRepository

    fun addZombieKills(): Int {
        zonPlayerRepository.addZombieKills(this)
        this.zombieKillCount = zonPlayerRepository.getZombieKills(this)
        return this.zombieKillCount
    }

    fun addStatusPoint(amount: Int) : Int {
        zonPlayerRepository.addStatusPoint(this, amount)
        this.statusPoint = zonPlayerRepository.getStatusPoint(this)
        return this.statusPoint
    }
}

class ZONPlayerService @Inject constructor(private val zonPlayerRepository: ZONPlayerRepository) {
    fun getZONPlayer(player : Player) : ZONPlayer{
        return zonPlayerRepository.getZONPlayer(player)
    }

}
