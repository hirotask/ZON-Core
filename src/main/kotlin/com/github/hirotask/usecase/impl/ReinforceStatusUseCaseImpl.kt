package com.github.hirotask.usecase.impl

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.usecase.ReinforceStatusUseCase
import com.github.hirotask.usecase.ZONPlayerActionUseCase
import org.bukkit.entity.Player
import javax.inject.Inject

class ReinforceStatusUseCaseImpl @Inject constructor(
        private val zonPlayerService: ZONPlayerService,
        private val zonPlayerActionUseCase: ZONPlayerActionUseCase
): ReinforceStatusUseCase {
    override fun reinforceHP(player: Player) {
        zonPlayerActionUseCase.invoke(player) {
            zonPlayerService.addStatusPoint(it, -1)
            zonPlayerService.addHP(it, 1)
        }
    }

    override fun reinforceHPRegen(player: Player) {
        zonPlayerActionUseCase.invoke(player) {
            zonPlayerService.addStatusPoint(it, -1)
            zonPlayerService.addHPRegen(it, 1)
        }
    }

    override fun reinforceMP(player: Player) {
        zonPlayerActionUseCase.invoke(player) {
            zonPlayerService.addStatusPoint(it, -1)
            zonPlayerService.addMP(it, 1)
        }
    }

    override fun reinforceMPRegen(player: Player) {
        zonPlayerActionUseCase.invoke(player) {
            zonPlayerService.addStatusPoint(it, -1)
            zonPlayerService.addMPRegen(it, 1)
        }
    }

    override fun reinforceStrength(player: Player) {
        zonPlayerActionUseCase.invoke(player) {
            zonPlayerService.addStatusPoint(it, -1)
            zonPlayerService.addStrength(it, 1)
        }
    }

}