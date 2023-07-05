package com.github.hirotask.usecase

import org.bukkit.entity.Player

/**
 * ステータス強化に関するユースケース
 *
 */
interface ReinforceStatusUseCase {

    /**
     * HPを強化する
     *
     * @param player 強化するプレイヤー
     */
    fun reinforceHP(player: Player)

    /**
     * HP再生速度を強化する
     *
     * @param player 強化するプレイヤー
     */
    fun reinforceHPRegen(player: Player)

    /**
     * MPを強化する
     *
     * @param player 強化するプレイヤー
     */
    fun reinforceMP(player: Player)

    /**
     * MP再生速度を強化する
     *
     * @param player 強化するプレイヤー
     */
    fun reinforceMPRegen(player: Player)

    /**
     * 攻撃力を強化する
     *
     * @param player 強化するプレイヤー
     */
    fun reinforceStrength(player: Player)
}
