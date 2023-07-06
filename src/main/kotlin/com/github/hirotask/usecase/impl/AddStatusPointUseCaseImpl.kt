package com.github.hirotask.usecase.impl

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.usecase.AddStatusPointUseCase
import com.github.hirotask.usecase.GetZONPlayerUseCase
import org.bukkit.entity.Player
import javax.inject.Inject

class AddStatusPointUseCaseImpl @Inject constructor(
    private val zonPlayerService: ZONPlayerService,
    private val getZonPlayerUseCase: GetZONPlayerUseCase
) : AddStatusPointUseCase {
    override fun invoke(playerName: String, playerUUID: String, value: Int): Int {
        val zonPlayer = getZonPlayerUseCase.invoke(playerName, playerUUID)
        return zonPlayerService.addStatusPoint(zonPlayer = zonPlayer, value)
    }
}
