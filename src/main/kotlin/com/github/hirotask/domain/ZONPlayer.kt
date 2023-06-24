package com.github.hirotask.domain

import org.bukkit.entity.Player

data class ZONPlayer(val player: Player, var zombieKillCount: Int, var statusPoint: Int) {
    // TODO: ドメインモデル貧血症が起こっているので、なにかドメインサービスとなる振る舞いを定義する
}
