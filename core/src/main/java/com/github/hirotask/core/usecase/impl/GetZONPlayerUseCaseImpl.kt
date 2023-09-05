package com.github.hirotask.core.usecase.impl

import com.github.hirotask.core.domain.ZONPlayer
import com.github.hirotask.core.domain.services.ZONPlayerService
import com.github.hirotask.core.usecase.GetZONPlayerUseCase
import javax.inject.Inject

class GetZONPlayerUseCaseImpl @Inject constructor(
    private val zonPlayerService: ZONPlayerService
) : GetZONPlayerUseCase {
    override fun invoke(playerName: String, playerUUID: String): ZONPlayer {
        return zonPlayerService.getZONPlayer(playerName, playerUUID)
    }
}
