package com.github.hirotask.core.domain.repository

import com.github.hirotask.core.domain.ZONPlayer

interface ZONPlayerRepository {

    /**
     * プレイヤーの名前を更新する
     *
     * @param zonPlayer
     * @param uuid
     * @return
     */
    fun updatePlayerName(zonPlayer: ZONPlayer, uuid: String): Int

    /**
     * ゾンビキル数を1増加する
     *
     * @param zonPlayer
     * @return
     */
    fun addZombieKills(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * ステータスポイントを増加する
     *
     * @param zonPlayer
     * @param amount
     * @return
     */
    fun addStatusPoint(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * ゾンビキル数を取得する
     *
     * @param zonPlayer
     * @return
     */
    fun getZombieKills(zonPlayer: ZONPlayer): Int

    /**
     * ステータスポイントを取得する
     *
     * @param zonPlayer
     * @return
     */
    fun getStatusPoint(zonPlayer: ZONPlayer): Int

    /**
     * ZONPlayerを取得する
     *
     * @param playerName プレイヤー名
     * @return
     */
    fun getZONPlayer(playerName: String, playerUUID: String): ZONPlayer

    /**
     * ZONPlayerを登録する
     *
     * @param playerName プレイヤー名
     * @return
     */
    fun addZONPlayer(playerName: String, playerUUID: String): Int
}
