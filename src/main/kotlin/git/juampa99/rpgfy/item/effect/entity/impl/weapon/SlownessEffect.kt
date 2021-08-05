package git.juampa99.rpgfy.item.effect.entity.impl.weapon

import git.juampa99.rpgfy.item.effect.entity.WeaponEffect
import git.juampa99.rpgfy.utils.number.cap
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.lang.Integer.min

object SlownessEffect : WeaponEffect("SLOWNESS") {

    override fun trigger(triggeredBy: LivingEntity,
                         target: LivingEntity, level: Int) {
        target.addPotionEffect(PotionEffect(PotionEffectType.SLOW, duration(level), level))
    }

    override fun description(): String = "Slows target"

    override fun duration(level: Int) : Int = level.cap(5) * MinecraftServer.TPS

    override fun isDebuff(): Boolean = true

    override fun cooldown(level: Int): Int = MinecraftServer.TPS * (10 - level.cap(5))

}