package com.github.hirotask.usecase

import org.bukkit.entity.Player

/**
 * 初回ログイン時のZONPlayer作成に関するユースケース
 *
 */
interface InitZONPlayerUseCase {

    /**
     * ユースケースを実行する
     *
     * @param player ログインしたプレイヤー
     */
    fun invoke(playerName: String, playerUUID: String)
}
