package com.github.hirotask.infra.zonplayer.impl

import com.github.hirotask.domain.ZONPlayer
import com.github.hirotask.domain.ZONPlayerStatus
import com.github.hirotask.exc.ZONPlayerStatusNotFoundException
import com.github.hirotask.infra.Database
import com.github.hirotask.infra.zonplayer.ZONPlayerStatusRepository
import org.bukkit.entity.Player

class ZONPlayerStatusRepositoryImpl(private val database: Database) : ZONPlayerStatusRepository {

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

    override fun getZONPlayerStatus(zonPlayer: ZONPlayer): ZONPlayerStatus {
        database.connect()
        val sql = """
            SELECT dh_hp, dhr_hp_regen, dm_mp, dmr_mp_regen, ds_strength FROM ms_players
            INNER JOIN dt_strength ON ms_players.mp_id = dt_strength.mp_id
            INNER JOIN dt_hp ON ms_players.mp_id = dt_hp.mp_id
            INNER JOIN dt_hp_regen ON ms_players.mp_id = dt_hp_regen.mp_id
            INNER JOIN dt_mp ON ms_players.mp_id = dt_mp.mp_id
            INNER JOIN dt_mp_regen ON ms_players.mp_id = dt_mp_regen.mp_id
            WHERE mp_name = '${zonPlayer.player.name}'
            ORDER BY dt_strength.created_at DESC, dt_hp.created_at DESC, dt_hp_regen.created_at DESC, dt_mp.created_at DESC, dt_mp_regen.created_at DESC;
        """.trimIndent()

        val rs = database.select(sql) ?: throw Exception()

        if (rs.first()) {
            val hp = rs.getInt("dh_hp")
            val hpRegen = rs.getInt("dhr_hp_regen")
            val mp = rs.getInt("dm_mp")
            val mpRegen = rs.getInt("dmr_mp_regen")
            val strength = rs.getInt("ds_strength")

            database.disconnect()

            return ZONPlayerStatus(
                hp = hp,
                hpRegen = hpRegen,
                mp = mp,
                mpRegen = mpRegen,
                strength = strength
            )
        } else {
            throw ZONPlayerStatusNotFoundException("player(${zonPlayer.player.name})'s status is not found in DB")
        }
    }

    override fun getHP(zonPlayer: ZONPlayer): Int {
        database.connect()

        val sql = """
            SELECT dh_hp FROM ms_players
                INNER JOIN dt_hp ON ms_players.mp_id = dt_hp.mp_id
                WHERE mp_name = '${zonPlayer.player.name}'
                ORDER BY created_at DESC LIMIT 1
        """.trimIndent()

        val rs = database.select(sql) ?: throw Exception()
        var hp = -1
        if (rs.first()) {
            hp = rs.getInt("dh_hp")
        }

        database.disconnect()

        return hp
    }

    override fun getHPRegen(zonPlayer: ZONPlayer): Int {
        database.connect()

        val sql = """
            SELECT dhr_hp_regen FROM ms_players
                INNER JOIN dt_hp_regen ON ms_players.mp_id = dt_hp_regen.mp_id
                WHERE mp_name = '${zonPlayer.player.name}'
                ORDER BY created_at DESC LIMIT 1
        """.trimIndent()

        val rs = database.select(sql) ?: throw Exception()
        var hpRegen = -1
        if (rs.first()) {
            hpRegen = rs.getInt("dhr_hp_regen")
        }

        database.disconnect()

        return hpRegen
    }

    override fun getMP(zonPlayer: ZONPlayer): Int {
        database.connect()

        val sql = """
            SELECT dm_mp FROM ms_players
                INNER JOIN dt_mp ON ms_players.mp_id = dt_mp.mp_id
                WHERE mp_name = '${zonPlayer.player.name}'
                ORDER BY created_at DESC LIMIT 1
        """.trimIndent()

        val rs = database.select(sql) ?: throw Exception()
        var mp = -1
        if (rs.first()) {
            mp = rs.getInt("dm_mp")
        }

        database.disconnect()

        return mp
    }

    override fun getMPRegen(zonPlayer: ZONPlayer): Int {
        database.connect()

        val sql = """
            SELECT dmr_mp_regen FROM ms_players
                INNER JOIN dt_mp_regen ON ms_players.mp_id = dt_mp_regen.mp_id
                WHERE mp_name = '${zonPlayer.player.name}'
                ORDER BY created_at DESC LIMIT 1
        """.trimIndent()

        val rs = database.select(sql) ?: throw Exception()
        var mpRegen = -1
        if (rs.first()) {
            mpRegen = rs.getInt("dmr_mp_regen")
        }

        database.disconnect()

        return mpRegen
    }

    override fun getStrength(zonPlayer: ZONPlayer): Int {
        database.connect()

        val sql = """
            SELECT ds_strength FROM ms_players
                INNER JOIN dt_strength ON ms_players.mp_id = dt_strength.mp_id
                WHERE mp_name = '${zonPlayer.player.name}'
                ORDER BY created_at DESC LIMIT 1
        """.trimIndent()

        val rs = database.select(sql) ?: throw Exception()
        var strength = -1
        if (rs.first()) {
            strength = rs.getInt("ds_strength")
        }

        database.disconnect()

        return strength
    }

    override fun addHP(zonPlayer: ZONPlayer, amount: Int): Int {
        val id = getPlayerId(zonPlayer.player)
        val value = zonPlayer.zonplayerStatus.hp + amount
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_hp(mp_id, dh_hp) VALUES ($id, $value)")
        database.disconnect()

        return result
    }

    override fun addHPRegen(zonPlayer: ZONPlayer, amount: Int): Int {
        val id = getPlayerId(zonPlayer.player)
        val value = zonPlayer.zonplayerStatus.hpRegen + amount
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_hp_regen(mp_id, dhr_hp_regen) VALUES ($id, $value)")
        database.disconnect()

        return result
    }

    override fun addMP(zonPlayer: ZONPlayer, amount: Int): Int {
        val id = getPlayerId(zonPlayer.player)
        val value = zonPlayer.zonplayerStatus.mp + amount
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_mp(mp_id, dm_mp) VALUES ($id, $value)")
        database.disconnect()

        return result
    }

    override fun addMPRegen(zonPlayer: ZONPlayer, amount: Int): Int {
        val id = getPlayerId(zonPlayer.player)
        val value = zonPlayer.zonplayerStatus.mpRegen + amount
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_mp_regen(mp_id, dmr_mp_regen) VALUES ($id, $value)")
        database.disconnect()

        return result
    }

    override fun addStrength(zonPlayer: ZONPlayer, amount: Int): Int {
        val id = getPlayerId(zonPlayer.player)
        val value = zonPlayer.zonplayerStatus.strength + amount
        database.connect()
        val result = database.createOrUpdateOrDelete("INSERT INTO dt_strength(mp_id, ds_strength) VALUES ($id, $value)")
        database.disconnect()

        return result
    }
}
