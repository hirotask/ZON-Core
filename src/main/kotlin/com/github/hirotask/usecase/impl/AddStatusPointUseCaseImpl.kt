package com.github.hirotask.usecase.impl

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.usecase.AddStatusPointUseCase
import com.github.hirotask.usecase.ZONPlayerActionUseCase
import org.bukkit.entity.Player
import javax.inject.Inject

class AddStatusPointUseCaseImpl @Inject constructor(
        private val zonPlayerService: ZONPlayerService,
        private val zonPlayerActionUseCase: ZONPlayerActionUseCase
) : AddStatusPointUseCase {
    override fun invoke(player: Player, value: Int) {
        zonPlayerActionUseCase.invoke(player) {
            zonPlayerService.addStatusPoint(zonPlayer = it, value)
        }
    }
}