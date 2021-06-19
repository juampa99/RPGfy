package git.juampa99.rpgfy.gear.effect.entity

import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object SlownessEffect : WeaponEffect(WeaponEffectEnum.SLOWNESS.name) {

    override fun trigger(triggeredBy: LivingEntity, target: LivingEntity, level: Int) {
        // Values may need some tweaking
        target.addPotionEffect(PotionEffect(PotionEffectType.SLOW, level * 50, level * 2))
    }

}