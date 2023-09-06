package testsupport

import com.github.hirotask.core.domain.ZONPlayer
import com.github.hirotask.core.domain.ZONPlayerStatus

class FakeZONPlayerFactory {
    fun createZONPlayer(playerName: String, playerUUID: String): ZONPlayer {
        val status = createZONPlayerStatus()
        return ZONPlayer(playerName, playerUUID, 1, 1, status)
    }

    fun createZONPlayerStatus(): ZONPlayerStatus {
        return ZONPlayerStatus(1, 1, 1, 1, 1)
    }
}
