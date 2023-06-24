package com.github.hirotask.infra.zonplayer.impl

import com.github.hirotask.domain.ZONPlayer
import com.github.hirotask.infra.Database
import com.github.hirotask.infra.zonplayer.ZONPlayerRepository
import org.bukkit.entity.Player

class ZONPlayerRepositoryImpl(val database: Database) : ZONPlayerRepository {

    override fun getPlayerId(player: Player): Int {
        database.connect()
        val rs = database.select("SELECT mp_id FROM ms_player WHERE mp_uuid = ${player.uniqueId}") ?: return -1

        var result = -1
        while (rs.next()) {
            result = rs.getInt("mp_id")
        }
        database.disconnect()

        return result
    }

    override fun updatePlayerName(zonPlayer: ZONPlayer, uuid: String): Int {
        database.connect()
        val result = database.createOrUpdateOrDelete("UPDATE ms_players SET mp_name = ${zonPlayer.player.name} WHERE mp_uuid = $uuid")
        database.disconnect()

        return result
    }

    override fun addZombieKills(playerId: Int, amount: Int): Int {
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_player_kills(mp_id, dpk_diff) VALUES ($playerId, $amount)")
        database.disconnect()

        return result
    }

    override fun addStatusPoint(playerId: Int, amount: Int): Int {
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_status_points(mp_id, dsp_diff) VALUES ($playerId, $amount)")
        database.disconnect()

        return result
    }

    override fun getZombieKills(playerId: Int): Int {
        database.connect()
        val rs = database.select("SELECT COUNT(*) count WHERE mp_id = $playerId") ?: return -1

        var result = -1
        while (rs.next()) {
            result = rs.getInt("count")
        }

        database.disconnect()

        return result
    }

    override fun getStatusPoint(playerId: Int): Int {
        database.connect()
        val rs = database.select("SELECT SUM(dsp_diff) diff WHERE mp_id = $playerId") ?: return -1

        var result = -1
        while (rs.next()) {
            result = rs.getInt("diff")
        }

        database.disconnect()

        return result
    }

    override fun getZONPlayer(player: Player): ZONPlayer {
        database.connect()
        val rs = database.select("SELECT COUNT(*) kills, SUM(dsp_diff) status_point FROM ms_players INNER JOIN dt_player_kills ON ms_players.mp_id = dt_player_kills.mp_id INNER JOIN dt_status_points ON ms_players.mp_id = dt_status_points.mp_id WHERE mp_name = ${player.name}") ?: throw Exception()

        var kills = -1
        var statusPoint = -1
        while (rs.next()) {
            kills = rs.getInt("kills")
            statusPoint = rs.getInt("status_point")
        }

        database.disconnect()

        return ZONPlayer(
                player= player,
                zombieKillCount = kills,
                statusPoint = statusPoint
        )

    }
}
