package com.github.hirotask.core.usecase.impl

import com.github.hirotask.core.domain.ZONPlayerService
import com.github.hirotask.core.usecase.GetZONPlayerUseCase
import com.github.hirotask.core.usecase.ReinforceStatusUseCase
import javax.inject.Inject

class ReinforceStatusUseCaseImpl @Inject constructor(
        private val zonPlayerService: ZONPlayerService,
        private val getZonPlayerUseCase: GetZONPlayerUseCase
) : ReinforceStatusUseCase {
    override fun reinforceHP(playerName: String, playerUUID: String) {
        val zonPlayer = getZonPlayerUseCase.invoke(playerName, playerUUID)
        zonPlayerService.addStatusPoint(zonPlayer, -1)
        zonPlayerService.addHP(zonPlayer, 1)
    }

    override fun reinforceHPRegen(playerName: String, playerUUID: String) {
        val zonPlayer = getZonPlayerUseCase.invoke(playerName, playerUUID)
        zonPlayerService.addStatusPoint(zonPlayer, -1)
        zonPlayerService.addHPRegen(zonPlayer, 1)
    }

    override fun reinforceMP(playerName: String, playerUUID: String) {
        val zonPlayer = getZonPlayerUseCase.invoke(playerName, playerUUID)
        zonPlayerService.addStatusPoint(zonPlayer, -1)
        zonPlayerService.addMP(zonPlayer, 1)
    }

    override fun reinforceMPRegen(playerName: String, playerUUID: String) {
        val zonPlayer = getZonPlayerUseCase.invoke(playerName, playerUUID)
        zonPlayerService.addStatusPoint(zonPlayer, -1)
        zonPlayerService.addMPRegen(zonPlayer, 1)
    }

    override fun reinforceStrength(playerName: String, playerUUID: String) {
        val zonPlayer = getZonPlayerUseCase.invoke(playerName, playerUUID)
        zonPlayerService.addStatusPoint(zonPlayer, -1)
        zonPlayerService.addStrength(zonPlayer, 1)
    }
}
