package com.github.hirotask.kills.inventory

import com.github.hirotask.kills.Main
import com.github.syari.spigot.api.inventory.CustomInventory
import com.github.syari.spigot.api.inventory.inventory
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

/**
 * ZONPlayerのステータス表示用インベントリを生成するクラス
 *
 * @property player 対象となるプレイヤー
 * @property onSkullClickInv プレイヤーヘッドをクリックしたときに開くインベントリ
 */
class StatusInventory(private val player: Player, private val main: Main, private val onSkullClickInv: ZONCustomInventory) : ZONCustomInventory {
    override val inventoryTitle = "ステータス"
    override fun create(): CustomInventory {

        val zonPlayer = main.getZonPlayerUseCase.invoke(player.name, player.uniqueId.toString())

        val playerSkull = ItemStack(Material.PLAYER_HEAD)
        val playerSkullMeta = (playerSkull.itemMeta as SkullMeta).apply {
            ownerProfile = player.playerProfile
            setDisplayName(player.name)
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
                    onSkullClickInv.create().open(player)
                }
            }
            for (i in 5..8) item(i, Material.GRAY_STAINED_GLASS_PANE, " ")
        }
    }
}
