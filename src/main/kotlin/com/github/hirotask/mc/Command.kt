package com.github.hirotask.mc

import com.github.hirotask.exc.ZONPlayerNotFoundException
import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.command.tab.CommandTabArgument.Companion.argument
import com.github.syari.spigot.api.inventory.inventory
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class Command(private val main: Main) {

    fun register() {
        main.command("zon") {
            description = "ZONPlayerに関するコマンド"
            aliases = listOf("zonpl")

            tab {
                argument { // 引数が何も入力されていない場合の補完
                    add("menu")
                }
            }

            execute { // コマンド実行時の処理
                when (args.lowerOrNull(0)) {
                    // 0 番目の引数が here だった時の処理
                    "menu" -> {
                        val player = sender as? Player ?: return@execute
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
                                item(4, playerSkull)
                                for (i in 5..8) item(i, Material.GRAY_STAINED_GLASS_PANE, " ")
                            }.open(player)
                        } catch (e: ZONPlayerNotFoundException) {
                            player.sendMessage("プレイヤーが取得できませんでした")
                        }
                    }
                    // 0 番目の引数が何も入力されていない時の処理
                    null -> {
                        sender.sendMessage(
                            """
                                §a/$label menu 自分のステータスを表示します
                            """.trimIndent()
                        )
                    }
                }
            }
        }
    }
}
