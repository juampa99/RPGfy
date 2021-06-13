package git.juampa99.rpgfy.healthbar.events

import git.juampa99.rpgfy.healthbar.util.HealthBarUtils.updateHealthBar
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntitySpawnEvent

class HealthBarListener : Listener {

    @EventHandler
    fun onEntitySpawn(event: EntitySpawnEvent) {
        updateHealthBar(event.entity)
    }

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        updateHealthBar(event.entity)
    }

    @EventHandler
    fun onEntityDead(event: EntityDeathEvent) {
        updateHealthBar(event.entity)
    }

}