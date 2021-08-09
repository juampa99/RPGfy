package git.juampa99.rpgfy.item.effect.entity.impl.armor

import git.juampa99.rpgfy.item.effect.entity.ArmorEffect
import git.juampa99.rpgfy.utils.number.cap
import git.juampa99.rpgfy.utils.number.effectCap
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.Bukkit.getLogger
import org.bukkit.Effect
import org.bukkit.entity.LivingEntity

object RepelEffect: ArmorEffect("REPEL") {

    override fun trigger(triggeredBy: LivingEntity,
                         target: LivingEntity, level: Int) {
        val targetPos = target.location.toVector()
        val playerPos = triggeredBy.location.toVector()
        val directionVector = targetPos.subtract(playerPos).normalize()

        target.velocity = directionVector.multiply(level.effectCap() * 0.1)
    }

    override fun description(): String = "Pushes back attacker"

    override fun duration(level: Int): Int = 0

    override fun isDebuff(): Boolean = false

    override fun cooldown(level: Int): Int = (10 - level.effectCap()) * MinecraftServer.TPS

}