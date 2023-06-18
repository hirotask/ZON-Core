package com.github.hirotask.infra

import org.bukkit.entity.Player
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager

class DAO {
    private val HOSTNAME = "mc_db"
    private val PORT = 3306
    private val DBNAME = "zonkills"
    private val USERNAME = "root"
    private val PASSWORD = "root"

    var conn: Connection

    init {
        Class.forName("org.mariadb.jdbc.Driver")
        conn = DriverManager.getConnection(
            "jdbc:mariadb://$HOSTNAME:$PORT/$DBNAME",
            USERNAME, PASSWORD
        )
    }

    fun insertPlayer(player: Player) {
        try {
            val stmt = conn.prepareStatement("INSERT INTO player_kills(player_name) values (?)")
            stmt.setString(1, player.name)
            stmt.executeUpdate()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getKills(player: Player): Int {
        return try {
            val stmt = conn.prepareStatement("SELECT COUNT(*) count FROM player_kills where player_name = ?;")
            stmt.setString(1, player.name)
            val rs = stmt.executeQuery()

            rs.next()
            rs.getInt("count")
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    }
}
