package git.juampa99.rpgfy.healthbar.service

import git.juampa99.rpgfy.Rpgfy
import git.juampa99.rpgfy.gear.effect.entity.Effect
import git.juampa99.rpgfy.utils.string.Color
import git.juampa99.rpgfy.utils.string.ColorCodes
import git.juampa99.rpgfy.utils.string.ColorCodes.getColorConstant
import git.juampa99.rpgfy.utils.string.FormatCodes
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getLogger
import org.bukkit.attribute.Attribute
import org.bukkit.entity.*
import java.util.*
import kotlin.math.floor

object HealthBarService {

    private val plugin = Rpgfy.plugin

    private val separator = plugin?.config?.get("separator").toString()

    private val entityEffects: MutableMap<UUID, MutableSet<Effect>> = mutableMapOf()

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
     * Takes a set of effects and returns a colored string representation
     * TODO: Add color and delimiter to plugin config
     * @param color of the effect string
     * @param effects Set of effects
     * @param delimiter string to separate effects with
     * @return string representation of the effect set
     * */
    private fun generateEffectsString(effects: Set<Effect>,
                                      color: Color, delimiter: String = "/"): String {
        if(effects.isEmpty()) return ""
        // Takes raw effect names and transforms them from EFFECT_NAME to Effect Name
        val effectString = " " + effects.joinToString(delimiter) { effect ->
            effect.name.split("_").joinToString(" ") {
                it.toLowerCase().capitalize()
            }
        }

        return color + effectString + FormatCodes.RESET
    }

    /**
     * Generates healthbar string
     * @param currentHealth Number on the left side of the slash
     * @param maxHealth Number on the right side of the slash
     * @return red string of the form [currentHealth/maxHealth]
     * */
    private fun generateHealthBar(currentHealth: Double,
                                  maxHealth: Double, color: Color): String {
        return color + "[${floor(currentHealth).toInt()}/${maxHealth.toInt()}]" + FormatCodes.RESET
    }

    private fun generateEntityName(entityName: String, color: Color): String {
        return "$color$separator$entityName" + FormatCodes.RESET
    }

    /**
     * Generate name with healthbar for entity of the form
     * @param entity Entity to generate tag for
     * @return String including entity name and current health [currentHealth/maxHealth] - EntityName
     */
    private fun generateTag(entity: LivingEntity): String {
        val maxHealth: Double = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value ?: return ""
        val currentHealth: Double = entity.health
        val entityName: String = extractName(entity.name)
        val effects = entityEffects[entity.uniqueId] ?: mutableSetOf()
        // This could crash if configs are missing
        val hbColor: Color = when(entity) {
            is Monster -> plugin?.config?.get("monster-healthbar-color").toString()
            is Player -> plugin?.config?.get("player-healthbar-color").toString()
            else -> plugin?.config?.get("animal-healthbar-color").toString()
        }

        return generateHealthBar(currentHealth, maxHealth, getColorConstant(hbColor)) +
                generateEffectsString(effects, getColorConstant("DARK_BLUE")) +
                 generateEntityName(entityName, ColorCodes.WHITE)
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

    fun addEffectToEntity(entity: Entity, effect: Effect) {
        val effectList = entityEffects[entity.uniqueId] ?: mutableSetOf()
        effectList.add(effect)
        entityEffects[entity.uniqueId] = effectList
    }

    fun removeEffectOfEntity(entity: Entity, effect: Effect) {
        val entityList = entityEffects[entity.uniqueId] ?: return
        entityList.remove(effect)
    }

}