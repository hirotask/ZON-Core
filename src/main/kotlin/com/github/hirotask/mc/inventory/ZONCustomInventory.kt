package com.github.hirotask.mc.inventory

import com.github.syari.spigot.api.inventory.CustomInventory

/**
 * ZON Serverで用いるカスタムインベントリ
 *
 */
interface ZONCustomInventory {

    /**
     * インベントリのタイトル
     */
    val inventoryTitle: String

    /**
     * インベントリを作成する
     *
     * @return 作成したインベントリ
     */
    fun create(): CustomInventory
}
