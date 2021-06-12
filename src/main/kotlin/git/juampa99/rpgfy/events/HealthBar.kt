package git.juampa99.rpgfy.events

import git.juampa99.rpgfy.Rpgfy
import org.bukkit.Bukkit.getLogger
import org.bukkit.Bukkit.getScheduler
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntitySpawnEvent
import java.util.*
import kotlin.math.floor

class HealthBar : Listener {

    private val plugin = Rpgfy.plugin

    // TODO: Modularize this
    /**
     * Generate name for entity
     */
    private fun generateName(entity: LivingEntity): String {
        // This should use some kind of table to store custom names set by players
        val currentHealth: Double = entity.health
        val entityName: String = entity.category.name

        return "§c[${floor(currentHealth)}/${entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value.toString()}] " +
                "§f" + entityName
    }

    // TODO: Modularize this
    /**
     * Updates health bar for entity
     * @param entity Entity to update health bar of
     * */
    private fun updateHealthBar(entity: Entity): Unit {
        if (plugin != null && entity is LivingEntity) {
            getScheduler().scheduleSyncDelayedTask(plugin, {
                entity.customName = generateName(entity)
            }, 5)
        }
        else getLogger().info("Plugin variable is null!")
    }

    @EventHandler
    fun onEntitySpawn(event: EntitySpawnEvent) {
        updateHealthBar(event.entity)
        event.entity.isCustomNameVisible = true
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