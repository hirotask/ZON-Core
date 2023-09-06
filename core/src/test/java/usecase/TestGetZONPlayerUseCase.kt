package usecase

import com.github.hirotask.core.domain.services.ZONPlayerService
import com.github.hirotask.core.usecase.impl.GetZONPlayerUseCaseImpl
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import testsupport.FakeZONPlayerFactory

class TestGetZONPlayerUseCase {
    private val zonPlayerService: ZONPlayerService = mockk()
    private val usecase = GetZONPlayerUseCaseImpl(zonPlayerService)
    private val zonPlayerFactory = FakeZONPlayerFactory()

    @BeforeEach
    fun setUp() {
        clearMocks(zonPlayerService)
    }

    @Test
    fun test_invoke() {
        val playerName = "hoge"
        val playerUUID = "hoge"
        val zonplayer = zonPlayerFactory.createZONPlayer(playerName, playerUUID)
        every { zonPlayerService.getZONPlayer(any(), any()) } returns zonplayer
        val result = usecase.invoke(playerName, playerUUID)

        assert(result == zonplayer)
    }
}
