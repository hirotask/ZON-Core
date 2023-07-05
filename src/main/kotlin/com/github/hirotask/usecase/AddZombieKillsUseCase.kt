package com.github.hirotask.usecase

import org.bukkit.entity.Player

/**
 * ゾンビキル数を追加するユースケース
 *
 */
interface AddZombieKillsUseCase {

    /**
     * ユースケースを実行する
     *
     * @param player Player
     * @param value ゾンビキル数増加量
     * @return 現在のゾンビキル数
     */
    fun invoke(player: Player, value: Int): Int
}
