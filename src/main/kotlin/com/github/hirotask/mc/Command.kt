package com.github.hirotask.mc

import com.github.hirotask.core.exc.ZONPlayerNotFoundException
import com.github.hirotask.mc.inventory.ReinForceInventory
import com.github.hirotask.mc.inventory.StatusInventory
import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.command.tab.CommandTabArgument.Companion.argument
import org.bukkit.ChatColor
import org.bukkit.entity.Player

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
                            val inv = StatusInventory(player, main, ReinForceInventory(player, main))
                            inv.create().open(player)
                        } catch (e: ZONPlayerNotFoundException) {
                            player.sendMessage("プレイヤーが取得できませんでした")
                        }
                    }
                    "addkills" -> {
                        val value = args.lowerOrNull(1) ?: return@execute
                        val valueInt = value.toInt()

                        try {
                            main.addZombieKillsUseCase.invoke(player.name, player.uniqueId.toString(), valueInt)
                            player.sendMessage("キル数を${valueInt}追加しました")
                        } catch (e: ZONPlayerNotFoundException) {
                            player.sendMessage("プレイヤーが取得できませんでした")
                        }
                    }
                    "addstatus" -> {
                        val value = args.lowerOrNull(1) ?: return@execute
                        val valueInt = value.toInt()

                        try {
                            main.addStatusPointUseCase.invoke(player.name, player.uniqueId.toString(), valueInt)
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
