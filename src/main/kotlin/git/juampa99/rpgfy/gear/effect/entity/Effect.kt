package git.juampa99.rpgfy.gear.effect.entity

import org.bukkit.entity.LivingEntity

interface Effect {

    val name: String

    fun trigger(triggeredBy: LivingEntity, target: LivingEntity, level: Int): Unit

    fun getDuration(level: Int): Int

}