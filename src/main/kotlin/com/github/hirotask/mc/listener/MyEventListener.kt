package com.github.hirotask.mc.listener

import com.github.hirotask.mc.Main
import com.github.hirotask.mc.event.ZombieDeathByPlayerEvent
import com.github.syari.spigot.api.event.events
import com.github.syari.spigot.api.sound.playSound
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.inventory.ItemStack

/**
 * 本プラグインで実装したイベントに対するリスナー
 *
 * @property main
 */
class MyEventListener(private val main: Main) {

    fun register() {
        main.events {
            event<ZombieDeathByPlayerEvent> {
                val player = it.player
                val zonPlayer = main.zonPlayerService.getZONPlayer(player)

                when (main.zonPlayerService.addZombieKills(zonPlayer)) {
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
