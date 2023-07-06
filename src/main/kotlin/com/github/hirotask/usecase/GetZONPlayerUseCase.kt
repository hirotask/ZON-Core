package com.github.hirotask.usecase

import com.github.hirotask.domain.ZONPlayer
import org.bukkit.entity.Player

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
