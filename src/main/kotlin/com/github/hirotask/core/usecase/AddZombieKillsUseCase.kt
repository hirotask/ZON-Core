package com.github.hirotask.core.usecase

/**
 * ゾンビキル数を追加するユースケース
 *
 */
interface AddZombieKillsUseCase {

    /**
     * ユースケースを実行する
     *
     * @param playerName プレイヤー名
     * @param value ゾンビキル数増加量
     * @return 現在のゾンビキル数
     */
    fun invoke(playerName: String, playerUUID: String, value: Int): Int
}
