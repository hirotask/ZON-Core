package com.github.hirotask.domain

import org.bukkit.entity.Player

/**
 * ZONPlayerドメイン
 *
 * @property player Player
 * @property zombieKillCount ゾンビキル数
 * @property statusPoint ステータスポイント
 */
data class ZONPlayer(val player: Player, var zombieKillCount: Int, var statusPoint: Int, var zonplayerStatus: ZONPlayerStatus)
