package com.github.hirotask.core.domain.services

import com.github.hirotask.core.domain.ZONPlayer

/**
 * ZONPlayerドメインのビジネスロジックに関わるクラス
 *
 */
interface ZONPlayerService {

    /**
     * ZONPlayerを追加する
     *
     * @param player Player
     * @return 追加したデータソースのインデックス
     */
    fun addZONPlayer(playerName: String, playerUUId: String): Int

    /**
     * ZONPlayerを取得する
     *
     * @param player Player
     * @return 取得したZONPlayer
     */
    fun getZONPlayer(playerName: String, playerUUID: String): ZONPlayer

    /**
     * ゾンビキル数を1増加する
     *
     * @param zonPlayer ZONPlayer
     * @return 現在のゾンビキル数
     */
    fun addZombieKills(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * ステータスポイントを追加する
     *
     * @param zonPlayer ZONPlayer
     * @param amount ステータスポイント追加量
     * @return 現在のステータスポイント値
     */
    fun addStatusPoint(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * HPを追加する
     *
     * @param zonPlayer
     * @param amount HP増加量
     * @return 現在のHP値
     */
    fun addHP(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * HP再生速度を増加させる
     *
     * @param zonPlayer
     * @param amount
     * @return 現在のHP再生速度
     */
    fun addHPRegen(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * MPを増加させる
     *
     * @param zonPlayer
     * @param amount
     * @return 現在のMP再生速度
     */
    fun addMP(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * MP再生速度を増加させる
     *
     * @param zonPlayer
     * @param amount
     * @return 現在のMP再生速度
     */
    fun addMPRegen(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * 攻撃力を増加させる
     *
     * @param zonPlayer
     * @param amount
     * @return 現在の攻撃力
     */
    fun addStrength(zonPlayer: ZONPlayer, amount: Int): Int
}
