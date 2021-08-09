package git.juampa99.rpgfy.item.effect.entity

import git.juampa99.rpgfy.utils.string.capitalizeFirst
import git.juampa99.rpgfy.utils.string.unslug
import org.bukkit.entity.LivingEntity

abstract class Effect(val name: String) {

    abstract fun trigger(triggeredBy: LivingEntity, target: LivingEntity, level: Int)

    abstract fun description(): String

    // Returns the effect duration in Ticks Per Second
    abstract fun duration(level: Int): Int

    abstract fun isDebuff(): Boolean

    abstract fun cooldown(level: Int): Int

    // e.g. name = POISON; toString() = Poison Effect
    override fun toString() = name.unslug() + " Effect"

}