package git.juampa99.rpgfy.item.effect.entity.impl.armor

import git.juampa99.rpgfy.Rpgfy
import git.juampa99.rpgfy.item.effect.entity.ArmorEffect
import git.juampa99.rpgfy.utils.number.effectCap
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getLogger
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Monster
import java.util.*

object FireAura: ArmorEffect("FIRE_AURA") {

     private val playerList = mutableMapOf<UUID, Boolean>()

     private fun recursiveTrigger(triggeredBy: LivingEntity, level: Int) {
         val cappedLevel = level.effectCap()
         val effectiveDistance = (cappedLevel * 2).toDouble()
         val damage = cappedLevel.toDouble() / 4
         val monstersInRange =
             triggeredBy.getNearbyEntities(effectiveDistance, effectiveDistance, effectiveDistance)
                 .filterIsInstance<Monster>()

         monstersInRange.forEach {
             monster ->
                 if(!monster.isDead) {
                     monster.damage(damage)
                     // Spawn fire particles
                     monster.world.spawnParticle(Particle.FLAME, monster.location, 20)
                 }
         }

        // If there are still alive monsters in range, keep proc-ing
        if(monstersInRange.any { monster -> !monster.isDead }) {
            Rpgfy.plugin?.let {
                Bukkit.getScheduler().scheduleSyncDelayedTask(it, {
                    recursiveTrigger(triggeredBy, level)
                }, cooldown(cappedLevel).toLong())
            }
        }
        // Otherwise, stop proc
        else
            playerList[triggeredBy.uniqueId] = false
    }

    override fun trigger(triggeredBy: LivingEntity, target: LivingEntity, level: Int) {
        // This has to be done this way since the value can be null
        if(playerList[triggeredBy.uniqueId] == true)
            return
        else
            playerList[triggeredBy.uniqueId] = true

        recursiveTrigger(triggeredBy, level)
    }

    override fun description(): String = "Damages attacking enemies in a\ndetermined radius around the player"

    override fun duration(level: Int): Int = 0

    override fun isDebuff(): Boolean = false

    override fun cooldown(level: Int): Int = MinecraftServer.TPS * (6 - level.effectCap())

}