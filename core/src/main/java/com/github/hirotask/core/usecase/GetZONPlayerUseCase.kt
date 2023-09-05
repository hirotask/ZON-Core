package com.github.hirotask.core.usecase

import com.github.hirotask.core.domain.ZONPlayer

/**
 * ZONPlayerを取得するだけのユースケース
 *
 */
interface GetZONPlayerUseCase {

    /**
     * ユースケースを実行する
     *
     * @param player Player
     * @return ZONPlayer
     */
    fun invoke(playerName: String, playerUUID: String): ZONPlayer
}
