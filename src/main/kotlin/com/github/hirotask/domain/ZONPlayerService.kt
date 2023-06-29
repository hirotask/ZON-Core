package com.github.hirotask.domain

import org.bukkit.entity.Player

interface ZONPlayerService {

    /**
     * ZONPlayerを追加する
     *
     * @param player Player
     * @return 追加したデータソースのインデックス
     */
    fun addZONPlayer(player: Player): Int

    /**
     * ZONPlayerを取得する
     *
     * @param player Player
     * @return 取得したZONPlayer
     */
    fun getZONPlayer(player: Player): ZONPlayer

    /**
     * ゾンビキル数を1増加する
     *
     * @param zonPlayer ZONPlayer
     * @return 追加したインデックス
     */
    fun addZombieKills(zonPlayer: ZONPlayer, amount: Int): Int

    /**
     * ステータスポイントを追加する
     *
     * @param zonPlayer ZONPlayer
     * @param amount ステータスポイント追加量
     * @return 追加したインデックス
     */
    fun addStatusPoint(zonPlayer: ZONPlayer, amount: Int): Int
}
