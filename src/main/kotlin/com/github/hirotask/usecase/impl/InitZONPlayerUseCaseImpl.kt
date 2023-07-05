package com.github.hirotask.usecase.impl

import com.github.hirotask.domain.ZONPlayerService
import com.github.hirotask.exc.ZONPlayerNotFoundException
import com.github.hirotask.usecase.InitZONPlayerUseCase
import org.bukkit.entity.Player
import javax.inject.Inject

class InitZONPlayerUseCaseImpl @Inject constructor(
        private val zonPlayerService: ZONPlayerService
) : InitZONPlayerUseCase {
    override fun invoke(player: Player) {
        try {
            zonPlayerService.getZONPlayer(player)
        } catch (e: ZONPlayerNotFoundException) {
            zonPlayerService.addZONPlayer(player)
        }
    }

}