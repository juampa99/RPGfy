package git.juampa99.rpgfy.gear.effect.entity.impl

import git.juampa99.rpgfy.gear.effect.entity.WeaponEffect
import git.juampa99.rpgfy.gear.effect.entity.WeaponEffectEnum
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object SlownessEffect : WeaponEffect(WeaponEffectEnum.SLOWNESS.name) {

    private const val TICKS_PER_SECOND = 20


    override fun trigger(triggeredBy: LivingEntity, target: LivingEntity, level: Int) {
        // Values may need some tweaking
        target.addPotionEffect(PotionEffect(PotionEffectType.SLOW, getDuration(level), level))
    }

    override fun getDuration(level: Int) : Int {
        return level * TICKS_PER_SECOND * 2
    }

}