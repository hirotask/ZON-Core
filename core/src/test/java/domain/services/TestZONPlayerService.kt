package domain.services

import com.github.hirotask.core.domain.ZONPlayer
import com.github.hirotask.core.domain.ZONPlayerStatus
import com.github.hirotask.core.domain.repository.ZONPlayerRepository
import com.github.hirotask.core.domain.repository.ZONPlayerStatusRepository
import com.github.hirotask.core.domain.services.ZONPlayerServiceImpl
import org.junit.jupiter.api.Test
import testsupport.FakeZONPlayerFactory
import testsupport.FakeZONPlayerRepository
import testsupport.FakeZONPlayerStatusRepository

class TestZONPlayerService {
    private val zonPlayerRepository: ZONPlayerRepository = FakeZONPlayerRepository()
    private val zonPlayerStatusRepository: ZONPlayerStatusRepository = FakeZONPlayerStatusRepository()
    private val zonPlayerServiceImpl = ZONPlayerServiceImpl(zonPlayerRepository, zonPlayerStatusRepository)
    private val zonPlayerFactory = FakeZONPlayerFactory()

    @Test
    fun test_addZONPlayer() {
        val testResult = zonPlayerServiceImpl.addZONPlayer("Hoge", "Hoge")
        assert(testResult == 1)
    }

    @Test
    fun test_getZONPlayer() {
        val playerName = "hoge"
        val playerUUID = "hoge"
        val testResult = zonPlayerServiceImpl.getZONPlayer(playerName, playerUUID)
        val expectedStatus = ZONPlayerStatus(1, 1, 1, 1, 1)
        val expected = ZONPlayer(playerName, playerUUID, 1, 1, expectedStatus)
        assert(testResult == expected)
    }

    @Test
    fun test_addZombieKills() {
        val amount = 1
        val zonPlayer = zonPlayerFactory.createZONPlayer("hoge", "hoge")
        val expected = zonPlayer.zombieKillCount + amount
        val testResult = zonPlayerServiceImpl.addZombieKills(zonPlayer, amount)
        assert(testResult == expected)
        assert(zonPlayer.zombieKillCount == expected)
    }

    @Test
    fun test_addStatusPoint() {
        val amount = 1
        val zonPlayer = zonPlayerFactory.createZONPlayer("huga", "huga")
        val expected = zonPlayer.statusPoint + amount
        val testResult = zonPlayerServiceImpl.addStatusPoint(zonPlayer, amount)
        assert(testResult == expected)
        assert(zonPlayer.statusPoint == expected)
    }

    @Test
    fun test_addHP() {
        val amount = 1
        val zonPlayer = zonPlayerFactory.createZONPlayer("huga", "huga")
        val expected = zonPlayer.zonplayerStatus.hp + amount
        val testResult = zonPlayerServiceImpl.addHP(zonPlayer, amount)
        assert(testResult == expected)
        assert(zonPlayer.zonplayerStatus.hp == expected)
    }

    @Test
    fun test_addHPRegen() {
        val amount = 1
        val zonPlayer = zonPlayerFactory.createZONPlayer("huga", "huga")
        val expected = zonPlayer.zonplayerStatus.hpRegen + amount
        val testResult = zonPlayerServiceImpl.addHPRegen(zonPlayer, amount)
        assert(testResult == expected)
        assert(zonPlayer.zonplayerStatus.hpRegen == expected)
    }

    @Test
    fun test_addMP() {
        val amount = 1
        val zonPlayer = zonPlayerFactory.createZONPlayer("huga", "huga")
        val expected = zonPlayer.zonplayerStatus.mp + amount
        val testResult = zonPlayerServiceImpl.addMP(zonPlayer, amount)
        assert(testResult == expected)
        assert(zonPlayer.zonplayerStatus.mp == expected)
    }

    @Test
    fun test_addMPRegen() {
        val amount = 1
        val zonPlayer = zonPlayerFactory.createZONPlayer("huga", "huga")
        val expected = zonPlayer.zonplayerStatus.mpRegen + amount
        val testResult = zonPlayerServiceImpl.addMPRegen(zonPlayer, amount)
        assert(testResult == expected)
        assert(zonPlayer.zonplayerStatus.mpRegen == expected)
    }

    @Test
    fun test_addStrength() {
        val amount = 1
        val zonPlayer = zonPlayerFactory.createZONPlayer("huga", "huga")
        val expected = zonPlayer.zonplayerStatus.strength + amount
        val testResult = zonPlayerServiceImpl.addStrength(zonPlayer, amount)
        assert(testResult == expected)
        assert(zonPlayer.zonplayerStatus.strength == expected)
    }
}
