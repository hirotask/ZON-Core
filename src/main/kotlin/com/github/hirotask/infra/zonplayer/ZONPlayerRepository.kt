package com.github.hirotask.infra.zonplayer

import com.github.hirotask.domain.ZONPlayer
import org.bukkit.entity.Player

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
     * @param player
     * @return
     */
    fun getZONPlayer(player: Player): ZONPlayer

    /**
     * ZONPlayerを登録する
     *
     * @param player
     * @return
     */
    fun addZONPlayer(player: Player): Int
}
