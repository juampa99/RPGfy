package git.juampa99.rpgfy.item.effect.entity.impl.weapon

import git.juampa99.rpgfy.item.effect.entity.WeaponEffect
import git.juampa99.rpgfy.utils.number.effectCap
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.entity.LivingEntity

object LightningEffect : WeaponEffect("LIGHTNING") {

    override fun trigger(triggeredBy: LivingEntity,
                         target: LivingEntity, level: Int) {
        target.world.strikeLightningEffect(target.location)
    }

    override fun description(): String = "Hits the target with a lightning strike"

    override fun duration(level: Int): Int {
        return 0
    }

    override fun isDebuff(): Boolean {
        return false
    }

    override fun cooldown(level: Int): Int {
        return MinecraftServer.TPS * (10 - level.effectCap())
    }

}