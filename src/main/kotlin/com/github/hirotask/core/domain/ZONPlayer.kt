package com.github.hirotask.core.domain


/**
 * ZONPlayerドメイン
 *
 * @property playerName プレイヤー名
 * @property zombieKillCount ゾンビキル数
 * @property statusPoint ステータスポイント
 */
data class ZONPlayer(val playerName: String, val playerUUID : String, var zombieKillCount: Int, var statusPoint: Int, val zonplayerStatus: ZONPlayerStatus)
