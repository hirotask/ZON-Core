package com.github.hirotask.infra.zonplayer.impl

import com.github.hirotask.domain.ZONPlayer
import com.github.hirotask.exc.ZONPlayerNotFoundException
import com.github.hirotask.infra.Database
import com.github.hirotask.infra.zonplayer.ZONPlayerRepository
import org.bukkit.entity.Player

/**
 * ZONPlayerRepositoryの実装クラス
 *
 * @property database
 */
class ZONPlayerRepositoryImpl(val database: Database) : ZONPlayerRepository {

    private fun getPlayerId(player: Player): Int {
        database.connect()
        val rs = database.select("SELECT mp_id FROM ms_players WHERE mp_uuid = '${player.uniqueId}'") ?: return -1

        var result = -1
        while (rs.next()) {
            result = rs.getInt("mp_id")
        }
        database.disconnect()

        return result
    }

    override fun updatePlayerName(zonPlayer: ZONPlayer, uuid: String): Int {
        database.connect()
        val result = database.createOrUpdateOrDelete("UPDATE ms_players SET mp_name = '${zonPlayer.player.name}' WHERE mp_uuid = '$uuid'")
        database.disconnect()

        return result
    }

    override fun addZombieKills(zonPlayer: ZONPlayer): Int {
        val id = getPlayerId(zonPlayer.player)
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_player_kills(mp_id) VALUES ($id)")
        database.disconnect()

        return result
    }

    override fun addStatusPoint(zonPlayer: ZONPlayer, amount: Int): Int {
        val id = getPlayerId(zonPlayer.player)
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_status_points(mp_id, dsp_diff) VALUES ($id, $amount)")
        database.disconnect()

        return result
    }

    override fun getZombieKills(zonPlayer: ZONPlayer): Int {
        database.connect()
        val rs = database.select("SELECT COUNT(*) count FROM ms_players INNER JOIN dt_player_kills ON ms_players.mp_id = dt_player_kills.mp_id WHERE mp_name = '${zonPlayer.player.name}'")
            ?: return -1

        var result = -1
        while (rs.next()) {
            result = rs.getInt("count")
        }

        database.disconnect()

        return result
    }

    override fun getStatusPoint(zonPlayer: ZONPlayer): Int {
        database.connect()
        val rs = database.select("SELECT SUM(dsp_diff) diff FROM ms_players INNER JOIN dt_status_points ON ms_players.mp_id = dt_status_points.mp_id WHERE mp_name = '${zonPlayer.player.name}'")
            ?: return -1

        var result = -1
        while (rs.next()) {
            result = rs.getInt("diff")
        }

        database.disconnect()

        return result
    }

    override fun getZONPlayer(player: Player): ZONPlayer {
        database.connect()
        val sql = "SELECT COUNT(dpk_id) kills, SUM(dsp_diff) status_point FROM dt_player_kills LEFT JOIN dt_status_points ON dt_player_kills.mp_id = dt_status_points.mp_id LEFT JOIN ms_players ON dt_player_kills.mp_id = ms_players.mp_id WHERE mp_name = '${player.name}'"
        val rs = database.select(sql) ?: throw Exception()

        var kills = 0
        var statusPoint = 0
        if (rs.first()) {
            if (rs.wasNull()) {
                throw ZONPlayerNotFoundException("player: ${player.name} is not found in DB")
            } else {
                kills = rs.getInt("kills")
                statusPoint = rs.getInt("status_point")
            }
        }
        database.disconnect()

        return ZONPlayer(
            player = player,
            zombieKillCount = kills,
            statusPoint = statusPoint
        )
    }

    override fun addZONPlayer(player: Player): Int {
        database.connect()
        val rs = database.createOrUpdateOrDelete("INSERT INTO ms_players(mp_name, mp_uuid) VALUES ('${player.name}', '${player.uniqueId}')")
        database.disconnect()

        return rs
    }
}
