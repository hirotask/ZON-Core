package com.github.hirotask.usecase

import org.bukkit.entity.Player

interface AddStatusPointUseCase {

    fun invoke(player : Player, value : Int) : Int
}