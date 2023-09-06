package usecase

import com.github.hirotask.core.domain.services.ZONPlayerService
import com.github.hirotask.core.usecase.GetZONPlayerUseCase
import com.github.hirotask.core.usecase.impl.AddZombieKillsUseCaseImpl
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import testsupport.FakeZONPlayerFactory

class TestAddZombieKillsUseCase {
    private val zonPlayerService: ZONPlayerService = mockk()
    private val zonPlayerUseCase: GetZONPlayerUseCase = mockk()
    private val usecase = AddZombieKillsUseCaseImpl(zonPlayerService, zonPlayerUseCase)
    private val zonPlayerFactory = FakeZONPlayerFactory()

    @BeforeEach
    fun setUp() {
        clearMocks(zonPlayerService, zonPlayerUseCase)
    }

    @Test
    fun test_invoke() {
        val playerName = "hoge"
        val playerUUID = "hoge"
        val value = 10
        val zonplayer = zonPlayerFactory.createZONPlayer(playerName, playerUUID)
        every { zonPlayerUseCase.invoke(any(), any()) } returns zonplayer
        every { zonPlayerService.addZombieKills(any(), any()) } returns (zonplayer.zombieKillCount + value)
        val result = usecase.invoke(playerName, playerUUID, value)

        assert(result == zonplayer.zombieKillCount + value)
    }
}
