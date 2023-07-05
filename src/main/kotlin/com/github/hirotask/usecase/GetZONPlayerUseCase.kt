package com.github.hirotask.usecase

import com.github.hirotask.domain.ZONPlayer
import org.bukkit.entity.Player

/**
 * ZONPlayerを取得するだけのユースケース
 *
 */
interface GetZONPlayerUseCase {

    fun invoke(player: Player): ZONPlayer
}