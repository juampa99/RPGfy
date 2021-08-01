package git.juampa99.rpgfy.healthbar.service

import git.juampa99.rpgfy.Rpgfy
import git.juampa99.rpgfy.item.effect.entity.Effect
import git.juampa99.rpgfy.utils.string.Color
import git.juampa99.rpgfy.utils.string.ColorCodes
import git.juampa99.rpgfy.utils.string.ColorCodes.getColorConstant
import git.juampa99.rpgfy.utils.string.FormatCodes
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.entity.*
import java.util.*
import kotlin.math.ceil
import kotlin.math.floor

object HealthBarService {

    private val plugin = Rpgfy.plugin
    private val entityEffects: MutableMap<UUID, MutableMap<String, Int>> = mutableMapOf()

    // Colors default to WHITE if config cant be retrieved
    private val separator: String =
        plugin?.config?.getString("separator") ?: ColorCodes.WHITE
    private val monsterHbColor: String =
        plugin?.config?.getString("monster-healthbar-color") ?: ColorCodes.WHITE
    private val playerHbColor: String =
        plugin?.config?.getString("player-healthbar-color") ?: ColorCodes.WHITE
    private val passiveMobHbColor: String =
        plugin?.config?.getString("animal-healthbar-color") ?: ColorCodes.WHITE
    private val barType =
        plugin?.config?.getString("healthbar-type") ?: "bar"



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
    private fun generateEffectsString(effects: Map<String, Int>,
                                      color: Color, delimiter: String = "/"): String {
        if(effects.isEmpty()) return ""
        // Takes raw effect names and transforms them from EFFECT_NAME to Effect Name
        val effectString = " " + effects.toList().joinToString { effect ->
            effect.first.split("_").joinToString(" ") {
                it.toLowerCase().capitalize()
            }
        }

        return color + effectString + FormatCodes.RESET
    }

    private fun generateNumericalHb(currentHealth: Double,
                                    maxHealth: Double, color: Color): String {
        return color + "[${ceil(currentHealth).toInt()}/${maxHealth.toInt()}]" + FormatCodes.RESET
    }

    private fun generateBarHb(currentHealth: Double,
                              maxHealth: Double, color: Color): String {
        val totalBars = ceil((currentHealth/maxHealth)*10).toInt()
        val healthBars = "|".repeat(totalBars)
        val missingHealthBars = "-".repeat(10-totalBars)

        return "$color[$healthBars$missingHealthBars]${FormatCodes.RESET}"
    }

    /**
     * Generates healthbar string
     * @param currentHealth Number on the left side of the slash
     * @param maxHealth Number on the right side of the slash
     * @return red string of the form [currentHealth/maxHealth]
     * */
    private fun generateHealthBar(currentHealth: Double,
                                  maxHealth: Double, color: Color): String {
        return if(barType == "numerical") generateNumericalHb(currentHealth, maxHealth, color)
               else generateBarHb(currentHealth, maxHealth, color)
    }

    private fun generateEntityName(entityName: String, separator: String, color: Color): String {
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
        val effects = entityEffects[entity.uniqueId] ?: mutableMapOf()
        // This could crash if configs are missing
        val hbColor: Color = when(entity) {
            is Monster -> monsterHbColor
            is Player -> playerHbColor
            else -> passiveMobHbColor
        }

        return generateHealthBar(currentHealth, maxHealth, getColorConstant(hbColor)) +
                generateEffectsString(effects, getColorConstant("DARK_BLUE")) +
                 generateEntityName(entityName, separator, ColorCodes.WHITE)
    }

    /**
     * Updates health bar for entity, effects must be added separately
     * @param entity Entity to update health bar of
     * */
    fun updateHealthBar(entity: Entity) {
        // Only usable for animals, mobs and player
        if(plugin == null || entity !is Creature) return
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, {
            if(plugin.config.getBoolean("show-pasive-animals-healthbar")
                || entity is Monster || entity is Player)
                entity.isCustomNameVisible = true
            entity.customName = generateTag(entity)
        }, 1)
    }

    /**
     * Adds effect to an entity. This will be displayed in the healthbar
     * if its still active when updated. Doesnt update automatically
     * updateHealthbar must be called
     * @param entity to add effect to
     * @param effect to add to  the entity
     * */
    fun addEffectToEntity(entity: Entity, effect: Effect) {
        val effectMap: MutableMap<String, Int> = entityEffects[entity.uniqueId] ?: mutableMapOf()
        // If the effect is already active in the entity,
        // add one to the integer in the tuple, else create a new pair
        effectMap[effect.name] = (effectMap[effect.name] ?: 0) + 1

        entityEffects[entity.uniqueId] = effectMap
    }

    /**
     * Removes effect from an entity. Doesnt update
     * automatically updateHealthbar must be called
     * @param entity to add effect to
     * @param effect to add to  the entity
     * */
    fun removeEffectOfEntity(entity: Entity, effect: Effect) {
        val effectMap = entityEffects[entity.uniqueId] ?: return
        var effectEntry = effectMap[effect.name]

        if(effectEntry != null) {
            if(effectEntry > 1 ) {
                effectEntry -= 1
                effectMap[effect.name] = effectEntry
            }
            else effectMap.remove(effect.name)
        }

    }

}