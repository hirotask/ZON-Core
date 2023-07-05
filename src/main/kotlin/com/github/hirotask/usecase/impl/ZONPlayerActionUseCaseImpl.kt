package com.github.hirotask.usecase.impl

import com.github.hirotask.domain.ZONPlayer
import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.usecase.ZONPlayerActionUseCase
import org.bukkit.entity.Player
import javax.inject.Inject

class ZONPlayerActionUseCaseImpl @Inject constructor(
        private val zonPlayerService: ZONPlayerService
): ZONPlayerActionUseCase {
    override fun invoke(player: Player, action: (ZONPlayer) -> Unit) {
        val zonPlayer = zonPlayerService.getZONPlayer(player)
        action(zonPlayer)
    }
}