package com.github.hirotask.usecase

import org.bukkit.entity.Player

interface ReinforceStatusUseCase {

    fun reinforceHP(player: Player)

    fun reinforceHPRegen(player: Player)

    fun reinforceMP(player: Player)

    fun reinforceMPRegen(player: Player)

    fun reinforceStrength(player: Player)
}
