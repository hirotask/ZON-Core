package com.github.hirotask.usecase

import org.bukkit.entity.Player

interface AddZombieKillsUseCase {

    fun invoke(player: Player, value: Int): Int
}
