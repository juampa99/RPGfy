package git.juampa99.rpgfy.item.effect.entity.impl.weapon

import git.juampa99.rpgfy.item.effect.entity.WeaponEffect
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.entity.LivingEntity

object LightningEffect : WeaponEffect("LIGHTNING") {

    override fun trigger(triggeredBy: LivingEntity,
                         target: LivingEntity, level: Int) {
        target.world.strikeLightning(target.location)
    }

    override fun getDuration(level: Int): Int {
        return 0
    }

    override fun isDebuff(): Boolean {
        return false
    }

    override fun getCooldown(level: Int): Int {
        return MinecraftServer.TPS * 10 - level
    }

}