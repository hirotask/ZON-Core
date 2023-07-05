package com.github.hirotask.usecase

import com.github.hirotask.domain.ZONPlayer
import org.bukkit.entity.Player

/**
 * PlayerからZONPlayerを取得し、何かするユースケース
 *
 */
interface ZONPlayerActionUseCase {

    fun invoke(player: Player, action : (ZONPlayer) -> Unit)
}