package com.github.hirotask.core.usecase

import org.bukkit.entity.Player

/**
 * ステータスポイントを追加するユースケース
 *
 */
interface AddStatusPointUseCase {

    /**
     * ユースケースを実行する
     *
     * @param playerName プレイヤー名
     * @param value ステータスポイントの増加量
     * @return 現在のステータスポイント
     */
    fun invoke(playerName: String, playerUUID: String, value: Int): Int
}
