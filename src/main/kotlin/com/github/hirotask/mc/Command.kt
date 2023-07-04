package com.github.hirotask.mc

import com.github.hirotask.exc.ValidNumberException
import com.github.hirotask.exc.ZONPlayerNotFoundException
import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.command.tab.CommandTabArgument.Companion.argument
import com.github.syari.spigot.api.inventory.inventory
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

/**
 * コマンドハンドラー
 *
 * @property main
 */
class Command(private val main: Main) {

    fun register() {
        main.command("zon") {
            description = "ZONPlayerに関するコマンド"
            aliases = listOf("zonpl")

            tab {
                argument { // 引数が何も入力されていない場合の補完
                    add("menu")
                    add("addkills")
                    add("addstatus")
                }
            }

            execute { // コマンド実行時の処理
                val player = sender as? Player ?: return@execute

                when (args.lowerOrNull(0)) {
                    // 0 番目の引数が here だった時の処理
                    "menu" -> {
                        try {
                            val zonPlayer = main.zonPlayerService.getZONPlayer(player)

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

                            inventory("ステータス", 1) {
                                for (i in 0..3) item(i, Material.GRAY_STAINED_GLASS_PANE, " ")
                                item(4, playerSkull) {
                                    onClick {
                                        inventory("ステータス強化", 1) {
                                            item(0, Material.GRAY_STAINED_GLASS_PANE)
                                            item(1, Material.RED_WOOL, display = "HP: ${zonPlayer.zonplayerStatus.hp}", lore = listOf("1ステータスポイントで強化する")) {
                                                onClick {
                                                    try {
                                                        main.zonPlayerService.addStatusPoint(zonPlayer, -1)
                                                        main.zonPlayerService.addHP(zonPlayer, 1)
                                                        player.sendMessage("HPを強化しました")
                                                    } catch (e: ValidNumberException) {
                                                        player.sendMessage("ステータスポイントが足りませんでした")
                                                    }
                                                }
                                            }
                                            item(2, Material.YELLOW_WOOL, display = "HP再生速度: ${zonPlayer.zonplayerStatus.hpRegen}", lore = listOf("1ステータスポイントで強化する")) {
                                                onClick {
                                                    try {
                                                        main.zonPlayerService.addStatusPoint(zonPlayer, -1)
                                                        main.zonPlayerService.addHPRegen(zonPlayer, 1)
                                                        player.sendMessage("HP再生速度を強化しました")
                                                    } catch (e: ValidNumberException) {
                                                        player.sendMessage("ステータスポイントが足りませんでした")
                                                    }
                                                }
                                            }
                                            item(3, Material.GREEN_WOOL, display = "MP: ${zonPlayer.zonplayerStatus.mp}", lore = listOf("1ステータスポイントで強化する")) {
                                                onClick {
                                                    try {
                                                        main.zonPlayerService.addStatusPoint(zonPlayer, -1)
                                                        main.zonPlayerService.addMP(zonPlayer, 1)
                                                        player.sendMessage("MPを強化しました")
                                                    } catch (e: ValidNumberException) {
                                                        player.sendMessage("ステータスポイントが足りませんでした")
                                                    }
                                                }
                                            }
                                            item(4, Material.CYAN_WOOL, display = "MP再生速度: ${zonPlayer.zonplayerStatus.mpRegen}", lore = listOf("1ステータスポイントで強化する")) {
                                                onClick {
                                                    try {
                                                        main.zonPlayerService.addStatusPoint(zonPlayer, -1)
                                                        main.zonPlayerService.addMPRegen(zonPlayer, 1)
                                                        player.sendMessage("MP再生速度を強化しました")
                                                    } catch (e: ValidNumberException) {
                                                        player.sendMessage("ステータスポイントが足りませんでした")
                                                    }
                                                }
                                            }
                                            item(5, Material.MAGENTA_WOOL, display = "攻撃力: ${zonPlayer.zonplayerStatus.strength}", lore = listOf("1ステータスポイントで強化する")) {
                                                onClick {
                                                    try {
                                                        main.zonPlayerService.addStatusPoint(zonPlayer, -1)
                                                        main.zonPlayerService.addStrength(zonPlayer, 1)
                                                        player.sendMessage("攻撃力を強化しました")
                                                    } catch (e: ValidNumberException) {
                                                        player.sendMessage("ステータスポイントが足りませんでした")
                                                    }
                                                }
                                            }
                                            for (i in 6..8) item(i, Material.GRAY_STAINED_GLASS_PANE)
                                        }.open(player)
                                    }
                                }
                                for (i in 5..8) item(i, Material.GRAY_STAINED_GLASS_PANE, " ")
                            }.open(player)
                        } catch (e: ZONPlayerNotFoundException) {
                            player.sendMessage("プレイヤーが取得できませんでした")
                        }
                    }
                    "addkills" -> {
                        val value = args.lowerOrNull(1) ?: return@execute
                        val valueInt = value.toInt()

                        try {
                            val zonPlayer = main.zonPlayerService.getZONPlayer(player)
                            main.zonPlayerService.addZombieKills(zonPlayer = zonPlayer, valueInt)
                            player.sendMessage("キル数を${valueInt}追加しました")
                        } catch (e: ZONPlayerNotFoundException) {
                            player.sendMessage("プレイヤーが取得できませんでした")
                        }
                    }
                    "addstatus" -> {
                        val value = args.lowerOrNull(1) ?: return@execute
                        val valueInt = value.toInt()

                        try {
                            val zonPlayer = main.zonPlayerService.getZONPlayer(player)
                            main.zonPlayerService.addStatusPoint(zonPlayer = zonPlayer, valueInt)
                            player.sendMessage("ステータスポイントを${valueInt}追加しました")
                        } catch (e: ZONPlayerNotFoundException) {
                            player.sendMessage("プレイヤーが取得できませんでした")
                        }
                    }
                    // 0 番目の引数が何も入力されていない時の処理
                    null -> {
                        sender.sendMessage(
                            """
                                ${ChatColor.GREEN}/$label menu ${ChatColor.WHITE}自分のステータスを表示します
                                ${ChatColor.GREEN}/$label addkills <数値> ${ChatColor.WHITE}キル数を増加させます
                                ${ChatColor.GREEN}/$label addstatus <数値> ${ChatColor.WHITE}ステータスポイントを増加させます
                            """.trimIndent()
                        )
                    }
                }
            }
        }
    }
}
