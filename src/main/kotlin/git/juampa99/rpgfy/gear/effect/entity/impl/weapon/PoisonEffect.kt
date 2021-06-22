package git.juampa99.rpgfy.gear.effect.entity.impl.weapon

import git.juampa99.rpgfy.gear.effect.entity.WeaponEffect
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object PoisonEffect : WeaponEffect("POISON") {

    override fun trigger(triggeredBy: LivingEntity,
                         target: LivingEntity, level: Int) {
        target.addPotionEffect(PotionEffect(PotionEffectType.POISON, getDuration(level), level))
    }

    override fun getDuration(level: Int): Int {
        return level * MinecraftServer.TPS
    }

    override fun isDebuff(): Boolean {
        return true
    }

    override fun getCooldown(level: Int): Int {
        return MinecraftServer.TPS * 10 - level
    }

}