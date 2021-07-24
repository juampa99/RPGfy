package git.juampa99.rpgfy.item.effect.entity

import git.juampa99.rpgfy.utils.string.capitalizeFirst
import org.bukkit.entity.LivingEntity

abstract class Effect(val name: String) {

    abstract fun trigger(triggeredBy: LivingEntity, target: LivingEntity, level: Int): Unit

    abstract fun getDuration(level: Int): Int

    abstract fun isDebuff(): Boolean

    abstract fun getCooldown(level: Int): Int

    // e.g. name = POISON; toString() = Poison Effect
    override fun toString() = name.lowercase().capitalizeFirst() + " Effect"

}