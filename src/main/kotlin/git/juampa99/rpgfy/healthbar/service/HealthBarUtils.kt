package git.juampa99.rpgfy.healthbar.service

import git.juampa99.rpgfy.Rpgfy
import git.juampa99.rpgfy.utils.string.Color
import git.juampa99.rpgfy.utils.string.ColorCodes
import git.juampa99.rpgfy.utils.string.ColorCodes.getColorConstant
import git.juampa99.rpgfy.utils.string.FormatCodes
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getLogger
import org.bukkit.attribute.Attribute
import org.bukkit.entity.*
import kotlin.math.floor

object HealthBarUtils {

    private val plugin = Rpgfy.plugin

    private val separator = plugin?.config?.get("separator").toString()

    /**
     * Extract name from an Entity displayName
     * @param fullName entity display name
     * @return name of entity
     * */
    private fun extractName(fullName: String): String {
        return if(!fullName.contains(separator)) fullName
        else fullName.substring(fullName.lastIndexOf(separator) + separator.length)
    }

    /**
     * Generates healthbar string
     * @param currentHealth Number on the left side of the slash
     * @param maxHealth Number on the right side of the slash
     * @return red string of the form [currentHealth/maxHealth]
     * */
    private fun generateHealthBar(currentHealth: Double, maxHealth: Double, color: Color): String {
        return color + "[${floor(currentHealth)}/${maxHealth}]"
    }

    /**
     * Generate name with healthbar for entity of the form
     * @param entity Entity to generate tag for
     * @return String including entity name and current health [currentHealth/maxHealth] - EntityName
     */
    private fun generateTag(entity: LivingEntity): String {
        val maxHealth: Double? = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value
        val currentHealth: Double = entity.health
        val entityName: String = extractName(entity.name)
        val hbColor: Color = when(entity) {
            is Monster -> plugin?.config?.get("monster-healthbar-color").toString()
            is Player -> plugin?.config?.get("player-healthbar-color").toString()
            else -> plugin?.config?.get("animal-healthbar-color").toString()
        }

        if(maxHealth == null) {
            getLogger().warning("null attribute for entity " + entity.name + " / " + entity.uniqueId)
            return ""
        }

        return generateHealthBar(currentHealth, maxHealth, getColorConstant(hbColor)) +
                "${FormatCodes.RESET}$separator$entityName"
    }



    /**
     * Updates health bar for entity
     * @param entity Entity to update health bar of
     * */
    fun updateHealthBar(entity: Entity) {
        // Only usable for animals, mobs and player
        if(plugin == null || entity !is Creature) return
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, {
            if(plugin.config.getBoolean("show-pasive-animals-healthbar") || entity is Monster || entity is Player)
                entity.isCustomNameVisible = true
            entity.customName = generateTag(entity)
        }, 1)
    }

}