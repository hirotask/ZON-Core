package com.github.hirotask.usecase.impl

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.usecase.AddZombieKillsUseCase
import org.bukkit.entity.Player
import javax.inject.Inject

class AddZombieKillsUseCaseImpl @Inject constructor(
        private val zonPlayerService: ZONPlayerService
) : AddZombieKillsUseCase{
    override fun invoke(player: Player, value : Int) {
        val zonPlayer = zonPlayerService.getZONPlayer(player)
        zonPlayerService.addZombieKills(zonPlayer = zonPlayer, value)
    }
}