package git.juampa99.rpgfy.item.effect.entity.impl.armor

import git.juampa99.rpgfy.item.effect.entity.ArmorEffect
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.Bukkit.getLogger
import org.bukkit.entity.LivingEntity

/**
 * Pushes back attacker (like a "reverse knock-back")
 * */
object RepelEffect: ArmorEffect("REPEL") {

    override fun trigger(triggeredBy: LivingEntity,
                         target: LivingEntity, level: Int) {

        getLogger().info("Repel effect triggered!")
        val targetPos = target.location.toVector()
        val playerPos = triggeredBy.location.toVector()

        val directionVector = targetPos.subtract(playerPos).normalize()

        target.velocity = directionVector.multiply(0.4)
    }

    override fun getDuration(level: Int): Int {
        return 0
    }

    override fun isDebuff(): Boolean {
        return false
    }

    override fun getCooldown(level: Int): Int {
        return 1 * MinecraftServer.TPS
    }


}