package testsupport

import com.github.hirotask.core.domain.ZONPlayer
import com.github.hirotask.core.domain.ZONPlayerStatus
import com.github.hirotask.core.domain.repository.ZONPlayerStatusRepository

class FakeZONPlayerStatusRepository : ZONPlayerStatusRepository {
    override fun getZONPlayerStatus(zonPlayer: ZONPlayer): ZONPlayerStatus {
        return ZONPlayerStatus(1, 1, 1, 1, 1)
    }

    override fun getHP(zonPlayer: ZONPlayer): Int {
        return zonPlayer.zonplayerStatus.hp
    }

    override fun getHPRegen(zonPlayer: ZONPlayer): Int {
        return zonPlayer.zonplayerStatus.hpRegen
    }

    override fun getMP(zonPlayer: ZONPlayer): Int {
        return zonPlayer.zonplayerStatus.mp
    }

    override fun getMPRegen(zonPlayer: ZONPlayer): Int {
        return zonPlayer.zonplayerStatus.mpRegen
    }

    override fun getStrength(zonPlayer: ZONPlayer): Int {
        return zonPlayer.zonplayerStatus.strength
    }

    override fun addHP(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayer.zonplayerStatus.hp += amount
        return zonPlayer.zonplayerStatus.hp
    }

    override fun addHPRegen(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayer.zonplayerStatus.hpRegen += amount
        return zonPlayer.zonplayerStatus.hpRegen
    }

    override fun addMP(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayer.zonplayerStatus.mp += amount
        return zonPlayer.zonplayerStatus.mp
    }

    override fun addMPRegen(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayer.zonplayerStatus.mpRegen += amount
        return zonPlayer.zonplayerStatus.mpRegen
    }

    override fun addStrength(zonPlayer: ZONPlayer, amount: Int): Int {
        zonPlayer.zonplayerStatus.strength += amount
        return zonPlayer.zonplayerStatus.strength
    }
}
