package com.github.hirotask.usecase

import com.github.hirotask.domain.ZONPlayer
import org.bukkit.entity.Player

/**
 * ZONPlayerを取得して何かやるユースケース
 *
 */
interface ZONPlayerActionUseCase {

    fun invoke(player: Player, action: (ZONPlayer) -> Unit)
}