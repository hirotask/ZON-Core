package com.github.hirotask.kills.inventory

import com.github.hirotask.core.exc.InvalidNumberException
import com.github.hirotask.kills.Main
import com.github.syari.spigot.api.inventory.CustomInventory
import com.github.syari.spigot.api.inventory.inventory
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * ステータス強化インベントリの生成を行うクラス
 *
 * @property zonPlayer 対象となるプレイヤー
 * @property main Mainインスタンス
 */
class ReinForceInventory(private val player: Player, private val main: Main) : ZONCustomInventory {
    override val inventoryTitle: String = "ステータス強化"

    override fun create(): CustomInventory {
        val zonPlayer = main.getZonPlayerUseCase.invoke(player.name, player.uniqueId.toString())

        return inventory(inventoryTitle, 1) {
            item(0, Material.GRAY_STAINED_GLASS_PANE)
            item(1, Material.RED_WOOL, display = "HP: ${zonPlayer.zonplayerStatus.hp}", lore = listOf("1ステータスポイントで強化する")) {
                onClick {
                    try {
                        main.reinforceStatusUseCase.reinforceHP(player.name, player.uniqueId.toString())
                        player.sendMessage("HPを強化しました")
                    } catch (e: InvalidNumberException) {
                        player.sendMessage("ステータスポイントが足りませんでした")
                    }
                }
            }
            item(2, Material.YELLOW_WOOL, display = "HP再生速度: ${zonPlayer.zonplayerStatus.hpRegen}", lore = listOf("1ステータスポイントで強化する")) {
                onClick {
                    try {
                        main.reinforceStatusUseCase.reinforceHPRegen(player.name, player.uniqueId.toString())
                        player.sendMessage("HP再生速度を強化しました")
                    } catch (e: InvalidNumberException) {
                        player.sendMessage("ステータスポイントが足りませんでした")
                    }
                }
            }
            item(3, Material.GREEN_WOOL, display = "MP: ${zonPlayer.zonplayerStatus.mp}", lore = listOf("1ステータスポイントで強化する")) {
                onClick {
                    try {
                        main.reinforceStatusUseCase.reinforceMP(player.name, player.uniqueId.toString())
                        player.sendMessage("MPを強化しました")
                    } catch (e: InvalidNumberException) {
                        player.sendMessage("ステータスポイントが足りませんでした")
                    }
                }
            }
            item(4, Material.CYAN_WOOL, display = "MP再生速度: ${zonPlayer.zonplayerStatus.mpRegen}", lore = listOf("1ステータスポイントで強化する")) {
                onClick {
                    try {
                        main.reinforceStatusUseCase.reinforceMPRegen(player.name, player.uniqueId.toString())
                        player.sendMessage("MP再生速度を強化しました")
                    } catch (e: InvalidNumberException) {
                        player.sendMessage("ステータスポイントが足りませんでした")
                    }
                }
            }
            item(5, Material.MAGENTA_WOOL, display = "攻撃力: ${zonPlayer.zonplayerStatus.strength}", lore = listOf("1ステータスポイントで強化する")) {
                onClick {
                    try {
                        main.reinforceStatusUseCase.reinforceStrength(player.name, player.uniqueId.toString())
                        player.sendMessage("攻撃力を強化しました")
                    } catch (e: InvalidNumberException) {
                        player.sendMessage("ステータスポイントが足りませんでした")
                    }
                }
            }
            for (i in 6..8) item(i, Material.GRAY_STAINED_GLASS_PANE)
        }
    }
}
