package com.github.hirotask.infra.zonplayer.impl

import com.github.hirotask.domain.ZONPlayer
import com.github.hirotask.domain.ZONPlayerStatus
import com.github.hirotask.exc.ZONPlayerNotFoundException
import com.github.hirotask.infra.Database
import com.github.hirotask.infra.zonplayer.ZONPlayerRepository
import org.bukkit.entity.Player

/**
 * ZONPlayerRepositoryの実装クラス
 *
 * @property database
 */
class ZONPlayerRepositoryImpl(private val database: Database) : ZONPlayerRepository {

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

    override fun addZombieKills(zonPlayer: ZONPlayer, amount: Int): Int {
        val id = getPlayerId(zonPlayer.player)
        val value = zonPlayer.zombieKillCount + amount
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_player_kills(mp_id, dpk_value) VALUES ($id, $value)")
        database.disconnect()

        return result
    }

    override fun addStatusPoint(zonPlayer: ZONPlayer, amount: Int): Int {
        val id = getPlayerId(zonPlayer.player)
        val value = zonPlayer.statusPoint + amount
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_status_points(mp_id, dsp_value) VALUES ($id, $value)")
        database.disconnect()

        return result
    }

    override fun getZombieKills(zonPlayer: ZONPlayer): Int {
        database.connect()
        val rs = database.select("SELECT dpk_value FROM ms_players INNER JOIN dt_player_kills ON ms_players.mp_id = dt_player_kills.mp_id WHERE mp_name = '${zonPlayer.player.name}' ORDER BY dpk_id DESC LIMIT 1")
            ?: return -1

        var result = -1
        while (rs.next()) {
            result = rs.getInt("dpk_value")
        }

        database.disconnect()

        return result
    }

    override fun getStatusPoint(zonPlayer: ZONPlayer): Int {
        database.connect()
        val rs = database.select("SELECT dsp_value FROM ms_players INNER JOIN dt_status_points ON ms_players.mp_id = dt_status_points.mp_id WHERE mp_name = '${zonPlayer.player.name}' ORDER BY dsp_id DESC LIMIT 1")
            ?: return -1

        var result = -1
        while (rs.next()) {
            result = rs.getInt("dsp_value")
        }

        database.disconnect()

        return result
    }

    override fun getZONPlayer(player: Player): ZONPlayer {
        database.connect()
        val sql = "SELECT dpk_value, dsp_value FROM dt_player_kills INNER JOIN dt_status_points ON dt_player_kills.mp_id = dt_status_points.mp_id INNER JOIN ms_players ON dt_player_kills.mp_id = ms_players.mp_id WHERE mp_name = '${player.name}' ORDER BY dt_player_kills.created_at DESC,dt_status_points.created_at DESC LIMIT 1"
        val rs = database.select(sql) ?: throw Exception()

        if (rs.first()) {
            val kills = rs.getInt("dpk_value")
            val statusPoint = rs.getInt("dsp_value")
            database.disconnect()

            return ZONPlayer(
                player = player,
                zombieKillCount = kills,
                statusPoint = statusPoint,
                zonplayerStatus = ZONPlayerStatus(
                    hp = 0,
                    hpRegen = 0,
                    mp = 0,
                    mpRegen = 0,
                    strength = 0
                ),
            )
        } else {
            throw ZONPlayerNotFoundException("player: ${player.name} is not found in DB")
        }
    }

    override fun addZONPlayer(player: Player): Int {
        database.connect()
        val rs = database.createOrUpdateOrDelete("INSERT INTO ms_players(mp_name, mp_uuid) VALUES ('${player.name}', '${player.uniqueId}')")
        database.disconnect()

        return rs
    }
}
