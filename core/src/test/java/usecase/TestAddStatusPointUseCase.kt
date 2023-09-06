package usecase

import com.github.hirotask.core.domain.ZONPlayer
import com.github.hirotask.core.domain.ZONPlayerStatus
import com.github.hirotask.core.domain.services.ZONPlayerService
import com.github.hirotask.core.usecase.GetZONPlayerUseCase
import com.github.hirotask.core.usecase.impl.AddStatusPointUseCaseImpl
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestAddStatusPointUseCase {

    private val zonPlayerService: ZONPlayerService = mockk()
    private val zonPlayerUseCase: GetZONPlayerUseCase = mockk()
    private val usecase = AddStatusPointUseCaseImpl(zonPlayerService, zonPlayerUseCase)

    @BeforeEach
    fun setUp() {
        clearMocks(zonPlayerService, zonPlayerUseCase)
    }

    @Test
    fun test_invoke() {
        val playerName = "hoge"
        val playerUUID = "hoge"
        val value = 10
        val zonplayer = createZONPlayer(playerName, playerUUID)
        every { zonPlayerUseCase.invoke(any(),any()) } returns zonplayer
        every { zonPlayerService.addStatusPoint(any(), any()) } returns (zonplayer.statusPoint + value)
        val result = usecase.invoke(playerName, playerUUID, value)

        assert(result == zonplayer.statusPoint + value)
    }

    private fun createZONPlayer(playerName: String, playerUUID: String): ZONPlayer {
        val status = createZONPlayerStatus()
        return ZONPlayer(playerName,playerUUID,1,1,status)
    }

    private fun createZONPlayerStatus() : ZONPlayerStatus {
        return ZONPlayerStatus(1,1,1,1,1)
    }

}