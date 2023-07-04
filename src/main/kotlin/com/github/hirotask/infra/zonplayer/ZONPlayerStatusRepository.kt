package com.github.hirotask.infra.zonplayer

import com.github.hirotask.domain.ZONPlayer
import com.github.hirotask.domain.ZONPlayerStatus

interface ZONPlayerStatusRepository {

    /**
     * プレイヤーのステータスを取得する
     *
     * @param zonPlayer
     * @return
     */
    fun getZONPlayerStatus(zonPlayer: ZONPlayer): ZONPlayerStatus

    /**
     * HPを取得する
     *
     * @param zonPlayer
     * @return
     */
    fun getHP(zonPlayer: ZONPlayer): Int

    /**
     * HP再生速度を取得する
     *
     * @param zonPlayer
     * @return
     */
    fun getHPRegen(zonPlayer: ZONPlayer): Int

    /**
     * MPを取得する
     *
     * @param zonPlayer
     * @return
     */
    fun getMP(zonPlayer: ZONPlayer): Int

    /**
     * MP再生速度を取得する
     *
     * @param zonPlayer
     * @return
     */
    fun getMPRegen(zonPlayer: ZONPlayer): Int

    /**
     * 攻撃力を取得する
     *
     * @param zonPlayer
     * @return
     */
    fun getStrength(zonPlayer: ZONPlayer): Int

    /**
     * HPを追加する
     *
     * @param zonPlayer
     * @param amount
     * @return
     */
    fun addHP(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * HP再生速度を追加する
     *
     * @param zonPlayer
     * @param amount
     * @return
     */
    fun addHPRegen(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * MPを追加する
     *
     * @param zonPlayer
     * @param amount
     * @return
     */
    fun addMP(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * MP再生速度を追加する
     *
     * @param zonPlayer
     * @param amount
     * @return
     */
    fun addMPRegen(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * 攻撃力を追加する
     *
     * @param zonPlayer
     * @param amount
     * @return
     */
    fun addStrength(zonPlayer: ZONPlayer, amount: Int): Int
}
