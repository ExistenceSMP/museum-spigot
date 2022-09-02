package com.existencesmp.museum

import net.axay.kspigot.event.listen
import net.axay.kspigot.main.KSpigot
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.util.Vector

class Museum : KSpigot() {
    companion object {
        lateinit var INSTANCE: Museum
    }

    override fun load() {
        INSTANCE = this
    }

    override fun startup() {
        listen<PlayerMoveEvent> {
            logger.info(it.player.world.name)
            logger.info(it.to.y.toString())
            if (it.player.world.name == "world" && it.to.y <= -3.0) {
                it.player.velocity = Vector(it.player.velocity.x, 1.0, it.player.velocity.z)
            }
        }
    }

    override fun shutdown() { }
}