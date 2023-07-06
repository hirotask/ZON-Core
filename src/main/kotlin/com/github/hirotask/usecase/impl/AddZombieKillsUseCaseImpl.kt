package com.github.hirotask.usecase.impl

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.usecase.AddZombieKillsUseCase
import com.github.hirotask.usecase.GetZONPlayerUseCase
import org.bukkit.entity.Player
import javax.inject.Inject

class AddZombieKillsUseCaseImpl @Inject constructor(
    private val zonPlayerService: ZONPlayerService,
    private val getZonPlayerUseCase: GetZONPlayerUseCase
) : AddZombieKillsUseCase {
    override fun invoke(playerName: String, playerUUID: String, value: Int): Int {
        val zonPlayer = getZonPlayerUseCase.invoke(playerName, playerUUID)
        return zonPlayerService.addZombieKills(zonPlayer = zonPlayer, value)
    }
}
