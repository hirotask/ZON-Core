package com.github.hirotask.domain

import com.github.hirotask.infra.zonplayer.ZONPlayerRepository
import com.github.hirotask.infra.zonplayer.ZONPlayerStatusRepository
import org.bukkit.entity.Player
import javax.inject.Inject

/**
 * ZONPlayerServiceの実装クラス
 *
 * @property zonPlayerRepository
 */
class ZONPlayerServiceImpl @Inject constructor(
        private val zonPlayerRepository: ZONPlayerRepository,
        private val zonPlayerStatusRepository: ZONPlayerStatusRepository
) : ZONPlayerService {
    override fun addZONPlayer(player: Player): Int {
        return zonPlayerRepository.addZONPlayer(player)
    }

    override fun getZONPlayer(player: Player): ZONPlayer {
        return zonPlayerRepository.getZONPlayer(player)
    }

    override fun addZombieKills(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayerRepository.addZombieKills(zonPlayer, amount)
        zonPlayer.zombieKillCount = zonPlayerRepository.getZombieKills(zonPlayer)
        return zonPlayer.zombieKillCount
    }

    override fun addStatusPoint(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayerRepository.addStatusPoint(zonPlayer, amount)
        zonPlayer.statusPoint = zonPlayerRepository.getStatusPoint(zonPlayer)
        return zonPlayer.statusPoint
    }

    override fun getPlayerStatus(zonPlayer: ZONPlayer): ZONPlayerStatus {
        return zonPlayerStatusRepository.getZONPlayerStatus(zonPlayer)
    }

    override fun addHP(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayerStatusRepository.addHP(zonPlayer, amount)
        zonPlayer.zonplayerStatus.hp = zonPlayerStatusRepository.getHP(zonPlayer)
        return zonPlayer.zonplayerStatus.hp
    }

    override fun addHPRegen(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayerStatusRepository.addHPRegen(zonPlayer, amount)
        zonPlayer.zonplayerStatus.hpRegen = zonPlayerStatusRepository.getHPRegen(zonPlayer)
        return zonPlayer.zonplayerStatus.hpRegen
    }

    override fun addMP(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayerStatusRepository.addMP(zonPlayer, amount)
        zonPlayer.zonplayerStatus.mp = zonPlayerStatusRepository.getMP(zonPlayer)
        return zonPlayer.zonplayerStatus.mp
    }

    override fun addMPRegen(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayerStatusRepository.addMPRegen(zonPlayer, amount)
        zonPlayer.zonplayerStatus.mpRegen = zonPlayerStatusRepository.getMPRegen(zonPlayer)
        return zonPlayer.zonplayerStatus.mpRegen
    }

    override fun addStrength(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayerStatusRepository.addStrength(zonPlayer, amount)
        zonPlayer.zonplayerStatus.strength = zonPlayerStatusRepository.getStrength(zonPlayer)
        return zonPlayer.zonplayerStatus.strength
    }
}
