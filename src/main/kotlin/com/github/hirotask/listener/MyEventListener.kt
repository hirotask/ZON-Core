package com.github.hirotask.listener

import com.github.hirotask.Main
import com.github.hirotask.event.ZombieDeathByPlayerEvent
import com.github.hirotask.infra.DAO
import com.github.syari.spigot.api.event.events
import com.github.syari.spigot.api.sound.playSound
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.inventory.ItemStack

object MyEventListener {
    fun register() {
        Main.INSTANCE.events {
            event<ZombieDeathByPlayerEvent> {
                val player = it.player

                val dao = DAO()
                dao.insertPlayer(player)

                val killCount = dao.getKills(player)

                player.sendMessage("Kills: $killCount")

                when (killCount) {
                    50 -> {
                        player.sendMessage("50キル達成！")
                        player.inventory.addItem(ItemStack(Material.DIAMOND))
                        player.playSound(Sound.ENTITY_ENDER_DRAGON_DEATH, volume = 0.5F)
                    }
                    100 -> {
                        player.sendMessage("100キル達成！")
                        player.inventory.addItem(ItemStack(Material.NETHER_STAR))
                        player.playSound(Sound.ENTITY_ENDER_DRAGON_DEATH, volume = 0.5F)
                    }
                }
            }
        }
    }
}