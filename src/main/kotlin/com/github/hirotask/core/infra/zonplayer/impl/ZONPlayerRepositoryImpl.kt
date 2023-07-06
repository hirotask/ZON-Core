package com.github.hirotask.core.infra.zonplayer.impl

import com.github.hirotask.core.domain.ZONPlayer
import com.github.hirotask.core.domain.ZONPlayerStatus
import com.github.hirotask.core.exc.InvalidNumberException
import com.github.hirotask.core.exc.ZONPlayerNotFoundException
import com.github.hirotask.core.infra.Database
import com.github.hirotask.core.infra.zonplayer.ZONPlayerRepository
import org.bukkit.entity.Player

/**
 * ZONPlayerRepositoryの実装クラス
 *
 * @property database
 */
class ZONPlayerRepositoryImpl(private val database: Database) : ZONPlayerRepository {

    private fun getPlayerId(playerName: String, playerUUID: String): Int {
        database.connect()
        val rs = database.select("SELECT mp_id FROM ms_players WHERE mp_name = '$playerName' AND mp_uuid = '$playerUUID'") ?: return -1

        var result = -1
        while (rs.next()) {
            result = rs.getInt("mp_id")
        }
        database.disconnect()

        return result
    }

    override fun updatePlayerName(zonPlayer: ZONPlayer, uuid: String): Int {
        database.connect()
        val result = database.createOrUpdateOrDelete("UPDATE ms_players SET mp_name = '${zonPlayer.playerName}' WHERE mp_uuid = '$uuid'")
        database.disconnect()

        return result
    }

    override fun addZombieKills(zonPlayer: ZONPlayer, amount: Int): Int {
        val id = getPlayerId(zonPlayer.playerName, zonPlayer.playerUUID)
        val value = zonPlayer.zombieKillCount + amount
        if (value < 0) {
            throw InvalidNumberException("ゾンビキル数は0以上でなければなりません")
        }
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_player_kills(mp_id, dpk_value) VALUES ($id, $value)")
        database.disconnect()

        return result
    }

    override fun addStatusPoint(zonPlayer: ZONPlayer, amount: Int): Int {
        val id = getPlayerId(zonPlayer.playerName, zonPlayer.playerUUID)
        val value = zonPlayer.statusPoint + amount
        if (value < 0) {
            throw InvalidNumberException("ステータスポイントは0以上でなければなりません")
        }

        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_status_points(mp_id, dsp_value) VALUES ($id, $value)")
        database.disconnect()

        return result
    }

    override fun getZombieKills(zonPlayer: ZONPlayer): Int {
        database.connect()
        val rs = database.select("SELECT dpk_value FROM ms_players INNER JOIN dt_player_kills ON ms_players.mp_id = dt_player_kills.mp_id WHERE mp_name = '${zonPlayer.playerName}' ORDER BY dpk_id DESC LIMIT 1")
                ?: return -1

        var result = 0
        while (rs.next()) {
            result = rs.getInt("dpk_value")
        }

        database.disconnect()

        return result
    }

    override fun getStatusPoint(zonPlayer: ZONPlayer): Int {
        database.connect()
        val rs = database.select("SELECT dsp_value FROM ms_players INNER JOIN dt_status_points ON ms_players.mp_id = dt_status_points.mp_id WHERE mp_name = '${zonPlayer.playerName}' ORDER BY dsp_id DESC LIMIT 1")
                ?: return -1

        var result = 0
        while (rs.next()) {
            result = rs.getInt("dsp_value")
        }

        database.disconnect()

        return result
    }

    override fun getZONPlayer(playerName: String, playerUUID: String): ZONPlayer {
        database.connect()
        val sql = "SELECT * FROM ms_players WHERE mp_name = '$playerName' AND mp_uuid = '$playerUUID' LIMIT 1"
        val rs = database.select(sql) ?: throw Exception()

        if (rs.first()) {
            database.disconnect()

            return ZONPlayer(
                    playerName = playerName,
                    playerUUID = playerUUID,
                    zombieKillCount = 0,
                    statusPoint = 0,
                    zonplayerStatus = ZONPlayerStatus(
                            hp = 0,
                            hpRegen = 0,
                            mp = 0,
                            mpRegen = 0,
                            strength = 0
                    ),
            )
        } else {
            throw ZONPlayerNotFoundException("player: $playerName is not found in DB")
        }
    }

    override fun addZONPlayer(playerName: String, playerUUID: String): Int {
        database.connect()
        val rs = database.createOrUpdateOrDelete("INSERT INTO ms_players(mp_name, mp_uuid) VALUES ('$playerName', '$playerUUID')")
        database.disconnect()

        return rs
    }
}
