package git.juampa99.rpgfy.gear.effect.entity

import org.bukkit.entity.LivingEntity

abstract class Effect(val name: String) {

    abstract fun trigger(triggeredBy: LivingEntity, target: LivingEntity, level: Int): Unit

    abstract fun getDuration(level: Int): Int

}