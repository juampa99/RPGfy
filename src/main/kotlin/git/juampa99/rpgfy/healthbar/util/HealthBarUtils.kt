package git.juampa99.rpgfy.healthbar.util

import git.juampa99.rpgfy.Rpgfy
import git.juampa99.rpgfy.utils.string.ColorCodes
import git.juampa99.rpgfy.utils.string.FormatCodes
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getLogger
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import kotlin.math.floor

object HealthBarUtils {

    private val plugin = Rpgfy.plugin

    /**
     * Generates healthbar string
     * @param currentHealth Number on the left side of the slash
     * @param maxHealth Number on the right side of the slash
     * @return red string of the form [currentHealth/maxHealth]
     * */
    private fun generateHealthBar(currentHealth: Double, maxHealth: Double): String {
        return ColorCodes.RED + "[${floor(currentHealth)}/${maxHealth}]"
    }

    /**
     * Generate name with healthbar for entity of the form
     * @param entity Entity to generate tag for
     * @return String including entity name and current health [currentHealth/maxHealth] - EntityName
     */
    private fun generateTag(entity: LivingEntity): String {
        // This should use some kind of table to store custom names set by players
        val maxHealth: Double? = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value
        val currentHealth: Double = entity.health
        val entityName: String = entity.category.name

        if(maxHealth == null) {
            getLogger().warning("null attribute for entity " + entity.name + " - " + entity.uniqueId)
            return ""
        }

        return generateHealthBar(currentHealth, maxHealth) + " - " + FormatCodes.RESET + entityName
    }

    /**
     * Updates health bar for entity
     * @param entity Entity to update health bar of
     * */
    fun updateHealthBar(entity: Entity) {
        // Only usable for animals and mobs
        if(plugin == null || entity !is LivingEntity || entity is Player) return
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, {
            entity.customName = generateTag(entity)
            entity.isCustomNameVisible = true
        }, 1)
    }

}