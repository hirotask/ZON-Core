package com.github.hirotask.core.usecase.impl

import com.github.hirotask.core.domain.services.ZONPlayerService
import com.github.hirotask.core.exc.ZONPlayerNotFoundException
import com.github.hirotask.core.usecase.InitZONPlayerUseCase
import javax.inject.Inject

class InitZONPlayerUseCaseImpl @Inject constructor(
    private val zonPlayerService: ZONPlayerService
) : InitZONPlayerUseCase {
    override fun invoke(playerName: String, playerUUID: String): Boolean {
        try {
            zonPlayerService.getZONPlayer(playerName, playerUUID)
            return true
        } catch (e: ZONPlayerNotFoundException) {
            zonPlayerService.addZONPlayer(playerName, playerUUID)
        }
        return false
    }
}
