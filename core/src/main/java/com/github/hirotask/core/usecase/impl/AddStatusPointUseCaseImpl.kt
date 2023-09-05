package com.github.hirotask.core.usecase.impl

import com.github.hirotask.core.domain.ZONPlayerService
import com.github.hirotask.core.usecase.AddStatusPointUseCase
import com.github.hirotask.core.usecase.GetZONPlayerUseCase
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
