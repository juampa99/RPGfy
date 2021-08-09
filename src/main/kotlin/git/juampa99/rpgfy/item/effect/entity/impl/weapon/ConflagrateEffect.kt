package git.juampa99.rpgfy.item.effect.entity.impl.weapon

import git.juampa99.rpgfy.item.effect.entity.WeaponEffect
import git.juampa99.rpgfy.utils.number.cap
import git.juampa99.rpgfy.utils.number.effectCap
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.Material
import org.bukkit.entity.LivingEntity
import org.bukkit.util.Vector

object ConflagrateEffect: WeaponEffect("CONFLAGRATE") {
    override fun trigger(triggeredBy: LivingEntity, target: LivingEntity, level: Int) {
        val targetPos = target.location.toVector()

        for(j in (-1)..1) {
            for(i in (-1)..1) {
                val blockPos = Vector(targetPos.x, targetPos.y - 1, targetPos.z + j)
                val blockPos2 = Vector(targetPos.x + i, targetPos.y, targetPos.z + j)
                val blockType = blockPos.toLocation(target.world).block.type
                val blockType2 = blockPos2.toLocation(target.world).block.type

                if(blockType != Material.AIR && blockType2 == Material.AIR)
                    blockPos2.toLocation(target.world).block.type = Material.FIRE
            }
        }

    }

    override fun description(): String = "Generates a circle of fire around the target"

    override fun duration(level: Int): Int = 0

    override fun isDebuff(): Boolean = false

    override fun cooldown(level: Int): Int {
        val seconds = (60 - (level.effectCap() * 6))
        return seconds * MinecraftServer.TPS
    }


}