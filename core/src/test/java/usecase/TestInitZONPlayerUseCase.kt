package usecase

import com.github.hirotask.core.domain.services.ZONPlayerService
import com.github.hirotask.core.exc.ZONPlayerNotFoundException
import com.github.hirotask.core.usecase.impl.InitZONPlayerUseCaseImpl
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import testsupport.FakeZONPlayerFactory

class TestInitZONPlayerUseCase {
    private val zonPlayerService: ZONPlayerService = mockk()
    private val usecase = InitZONPlayerUseCaseImpl(zonPlayerService)
    private val zonPlayerFactory = FakeZONPlayerFactory()

    @BeforeEach
    fun setUp() {
        clearMocks(zonPlayerService)
    }

    @Test
    fun test_invoke_success() {
        val playerName = "hoge"
        val playerUUID = "hoge"
        val zonplayer = zonPlayerFactory.createZONPlayer(playerName, playerUUID)
        every { zonPlayerService.getZONPlayer(any(), any()) } returns zonplayer
        val result = usecase.invoke(playerName, playerUUID)

        assert(result)
    }

    @Test
    fun test_invoke_failure() {
        val playerName = "hoge"
        val playerUUID = "hoge"
        every { zonPlayerService.getZONPlayer(any(), any()) } throws ZONPlayerNotFoundException()
        every { zonPlayerService.addZONPlayer(any(), any()) } returns 1
        val result = usecase.invoke(playerName, playerUUID)

        assert(!result)
    }
}
