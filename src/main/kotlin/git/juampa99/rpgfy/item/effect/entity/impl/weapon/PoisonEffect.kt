package git.juampa99.rpgfy.item.effect.entity.impl.weapon

import git.juampa99.rpgfy.item.effect.entity.WeaponEffect
import git.juampa99.rpgfy.utils.number.cap
import git.juampa99.rpgfy.utils.number.effectCap
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object PoisonEffect : WeaponEffect("POISON") {

    override fun trigger(triggeredBy: LivingEntity,
                         target: LivingEntity, level: Int) {
        target.addPotionEffect(PotionEffect(PotionEffectType.POISON,
            duration(level.effectCap()), level.effectCap()))
    }

    override fun description(): String = "Poisons target"

    override fun duration(level: Int): Int = level * MinecraftServer.TPS

    override fun isDebuff(): Boolean = true

    override fun cooldown(level: Int): Int = (MinecraftServer.TPS * 10) - level.effectCap()

}