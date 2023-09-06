package testsupport

import com.github.hirotask.core.domain.ZONPlayer
import com.github.hirotask.core.domain.ZONPlayerStatus
import com.github.hirotask.core.domain.repository.ZONPlayerRepository

class FakeZONPlayerRepository : ZONPlayerRepository {
    override fun updatePlayerName(zonPlayer: ZONPlayer, uuid: String): Int {
        return 1
    }

    override fun addZombieKills(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayer.zombieKillCount += amount
        return zonPlayer.zombieKillCount
    }

    override fun addStatusPoint(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayer.statusPoint += amount
        return zonPlayer.statusPoint
    }

    override fun getZombieKills(zonPlayer: ZONPlayer): Int {
        return zonPlayer.zombieKillCount
    }

    override fun getStatusPoint(zonPlayer: ZONPlayer): Int {
        return zonPlayer.statusPoint
    }

    override fun getZONPlayer(playerName: String, playerUUID: String): ZONPlayer {
        val status = ZONPlayerStatus(1, 1, 1, 1, 1)
        return ZONPlayer(playerName, playerUUID, 1, 1, status)
    }

    override fun addZONPlayer(playerName: String, playerUUID: String): Int {
        return 1
    }
}
