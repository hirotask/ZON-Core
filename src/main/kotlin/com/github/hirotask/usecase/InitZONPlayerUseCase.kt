package com.github.hirotask.usecase

import org.bukkit.entity.Player

/**
 * 初回ログイン時のZONPlayer作成に関するユースケース
 *
 */
interface InitZONPlayerUseCase {

    fun invoke(player: Player)
}