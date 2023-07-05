package com.github.hirotask.usecase.impl

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.usecase.AddStatusPointUseCase
import org.bukkit.entity.Player
import javax.inject.Inject

class AddStatusPointUseCaseImpl @Inject constructor(
        private val zonPlayerService: ZONPlayerService,
) : AddStatusPointUseCase {
    override fun invoke(player: Player, value: Int): Int {
        val zonPlayer = zonPlayerService.getZONPlayer(player)
        return zonPlayerService.addStatusPoint(zonPlayer = zonPlayer, value)
    }
}