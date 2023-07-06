package com.github.hirotask.core.usecase

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
    fun reinforceHP(playerName: String, playerUUID: String)

    /**
     * HP再生速度を強化する
     *
     * @param player 強化するプレイヤー
     */
    fun reinforceHPRegen(playerName: String, playerUUID: String)

    /**
     * MPを強化する
     *
     * @param player 強化するプレイヤー
     */
    fun reinforceMP(playerName: String, playerUUID: String)

    /**
     * MP再生速度を強化する
     *
     * @param player 強化するプレイヤー
     */
    fun reinforceMPRegen(playerName: String, playerUUID: String)

    /**
     * 攻撃力を強化する
     *
     * @param player 強化するプレイヤー
     */
    fun reinforceStrength(playerName: String, playerUUID: String)
}
