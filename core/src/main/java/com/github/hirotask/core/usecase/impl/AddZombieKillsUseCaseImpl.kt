package com.github.hirotask.core.usecase.impl

import com.github.hirotask.core.domain.services.ZONPlayerService
import com.github.hirotask.core.usecase.AddZombieKillsUseCase
import com.github.hirotask.core.usecase.GetZONPlayerUseCase
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
