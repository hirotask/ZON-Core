package com.github.hirotask.mc.inventory

import com.github.hirotask.domain.ZONPlayer
import com.github.hirotask.exc.InvalidNumberException
import com.github.hirotask.mc.Main
import com.github.syari.spigot.api.inventory.CustomInventory
import com.github.syari.spigot.api.inventory.inventory
import org.bukkit.Material

class ReinForceInventory(private val zonPlayer: ZONPlayer, private val main: Main) : ZONCustomInventory {
    override val inventoryTitle: String = "ステータス強化"

    override fun create(): CustomInventory {
        return inventory(inventoryTitle, 1) {
            item(0, Material.GRAY_STAINED_GLASS_PANE)
            item(1, Material.RED_WOOL, display = "HP: ${zonPlayer.zonplayerStatus.hp}", lore = listOf("1ステータスポイントで強化する")) {
                onClick {
                    try {
                        main.zonPlayerService.addStatusPoint(zonPlayer, -1)
                        main.zonPlayerService.addHP(zonPlayer, 1)
                        zonPlayer.player.sendMessage("HPを強化しました")
                    } catch (e: InvalidNumberException) {
                        zonPlayer.player.sendMessage("ステータスポイントが足りませんでした")
                    }
                }
            }
            item(2, Material.YELLOW_WOOL, display = "HP再生速度: ${zonPlayer.zonplayerStatus.hpRegen}", lore = listOf("1ステータスポイントで強化する")) {
                onClick {
                    try {
                        main.zonPlayerService.addStatusPoint(zonPlayer, -1)
                        main.zonPlayerService.addHPRegen(zonPlayer, 1)
                        zonPlayer.player.sendMessage("HP再生速度を強化しました")
                    } catch (e: InvalidNumberException) {
                        zonPlayer.player.sendMessage("ステータスポイントが足りませんでした")
                    }
                }
            }
            item(3, Material.GREEN_WOOL, display = "MP: ${zonPlayer.zonplayerStatus.mp}", lore = listOf("1ステータスポイントで強化する")) {
                onClick {
                    try {
                        main.zonPlayerService.addStatusPoint(zonPlayer, -1)
                        main.zonPlayerService.addMP(zonPlayer, 1)
                        zonPlayer.player.sendMessage("MPを強化しました")
                    } catch (e: InvalidNumberException) {
                        zonPlayer.player.sendMessage("ステータスポイントが足りませんでした")
                    }
                }
            }
            item(4, Material.CYAN_WOOL, display = "MP再生速度: ${zonPlayer.zonplayerStatus.mpRegen}", lore = listOf("1ステータスポイントで強化する")) {
                onClick {
                    try {
                        main.zonPlayerService.addStatusPoint(zonPlayer, -1)
                        main.zonPlayerService.addMPRegen(zonPlayer, 1)
                        zonPlayer.player.sendMessage("MP再生速度を強化しました")
                    } catch (e: InvalidNumberException) {
                        zonPlayer.player.sendMessage("ステータスポイントが足りませんでした")
                    }
                }
            }
            item(5, Material.MAGENTA_WOOL, display = "攻撃力: ${zonPlayer.zonplayerStatus.strength}", lore = listOf("1ステータスポイントで強化する")) {
                onClick {
                    try {
                        main.zonPlayerService.addStatusPoint(zonPlayer, -1)
                        main.zonPlayerService.addStrength(zonPlayer, 1)
                        zonPlayer.player.sendMessage("攻撃力を強化しました")
                    } catch (e: InvalidNumberException) {
                        zonPlayer.player.sendMessage("ステータスポイントが足りませんでした")
                    }
                }
            }
            for (i in 6..8) item(i, Material.GRAY_STAINED_GLASS_PANE)
        }
    }
}
