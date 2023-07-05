package com.github.hirotask.usecase

import org.bukkit.entity.Player

/**
 * ステータスポイントを追加するユースケース
 *
 */
interface AddStatusPointUseCase {

    /**
     * ユースケースを実行する
     *
     * @param player Player
     * @param value ステータスポイントの増加量
     * @return 現在のステータスポイント
     */
    fun invoke(player: Player, value: Int): Int
}
