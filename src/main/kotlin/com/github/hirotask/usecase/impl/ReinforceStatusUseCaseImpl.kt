package com.github.hirotask.usecase.impl

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.usecase.GetZONPlayerUseCase
import com.github.hirotask.usecase.ReinforceStatusUseCase
import org.bukkit.entity.Player
import javax.inject.Inject

class ReinforceStatusUseCaseImpl @Inject constructor(
    private val zonPlayerService: ZONPlayerService,
    private val getZonPlayerUseCase: GetZONPlayerUseCase
) : ReinforceStatusUseCase {
    override fun reinforceHP(player: Player) {
        val zonPlayer = getZonPlayerUseCase.invoke(player)
        zonPlayerService.addStatusPoint(zonPlayer, -1)
        zonPlayerService.addHP(zonPlayer, 1)
    }

    override fun reinforceHPRegen(player: Player) {
        val zonPlayer = getZonPlayerUseCase.invoke(player)
        zonPlayerService.addStatusPoint(zonPlayer, -1)
        zonPlayerService.addHPRegen(zonPlayer, 1)
    }

    override fun reinforceMP(player: Player) {
        val zonPlayer = getZonPlayerUseCase.invoke(player)
        zonPlayerService.addStatusPoint(zonPlayer, -1)
        zonPlayerService.addMP(zonPlayer, 1)
    }

    override fun reinforceMPRegen(player: Player) {
        val zonPlayer = getZonPlayerUseCase.invoke(player)
        zonPlayerService.addStatusPoint(zonPlayer, -1)
        zonPlayerService.addMPRegen(zonPlayer, 1)
    }

    override fun reinforceStrength(player: Player) {
        val zonPlayer = getZonPlayerUseCase.invoke(player)
        zonPlayerService.addStatusPoint(zonPlayer, -1)
        zonPlayerService.addStrength(zonPlayer, 1)
    }
}
