package com.github.hirotask.usecase.impl

import com.github.hirotask.domain.ZONPlayer
import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.usecase.GetZONPlayerUseCase
import org.bukkit.entity.Player
import javax.inject.Inject

class GetZONPlayerUseCaseImpl @Inject constructor(
    private val zonPlayerService: ZONPlayerService
) : GetZONPlayerUseCase {
    override fun invoke(player: Player): ZONPlayer {
        return zonPlayerService.getZONPlayer(player)
    }
}
