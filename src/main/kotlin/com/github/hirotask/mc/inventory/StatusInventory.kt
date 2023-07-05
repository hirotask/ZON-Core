package com.github.hirotask.mc.inventory

import com.github.hirotask.domain.ZONPlayer
import com.github.syari.spigot.api.inventory.CustomInventory
import com.github.syari.spigot.api.inventory.inventory
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class StatusInventory(private val zonPlayer : ZONPlayer, private val onSkullClickInv : ZONCustomInventory) : ZONCustomInventory {
    override val inventoryTitle = "ステータス"
    override fun create(): CustomInventory {
        val playerSkull = ItemStack(Material.PLAYER_HEAD)
        val playerSkullMeta = (playerSkull.itemMeta as SkullMeta).apply {
            ownerProfile = zonPlayer.player.playerProfile
            setDisplayName(zonPlayer.player.name)
            lore = listOf(
                    "ゾンビキル数：${zonPlayer.zombieKillCount}",
                    "ステータスポイント：${zonPlayer.statusPoint}"
            )
        }
        playerSkull.itemMeta = playerSkullMeta

        return inventory(inventoryTitle, 1) {
            for (i in 0..3) item(i, Material.GRAY_STAINED_GLASS_PANE, " ")
            item(4, playerSkull) {
                onClick {
                    onSkullClickInv.create().open(zonPlayer.player)
                }
            }
            for (i in 5..8) item(i, Material.GRAY_STAINED_GLASS_PANE, " ")
        }
    }
}