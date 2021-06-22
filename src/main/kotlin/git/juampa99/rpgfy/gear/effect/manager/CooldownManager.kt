package git.juampa99.rpgfy.gear.effect.manager

import git.juampa99.rpgfy.gear.effect.entity.Effect
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import java.util.*
import kotlin.math.abs

object CooldownManager {

    private val cooldown: MutableMap<UUID, MutableMap<String, Int>> = mutableMapOf()

    /**
     * Returns 0 if effect has faded, otherwise the n of ticks to fade
     * */
    fun getCooldown(entity: LivingEntity, effect: Effect): Int {
        val effectList = cooldown[entity.uniqueId] ?: return 0

        val readyOnTick = effectList[effect.name] ?: return 0

        return if(readyOnTick < MinecraftServer.currentTick) 0
                else abs(readyOnTick - MinecraftServer.currentTick)
    }

    /**
     * Stores expiring tick of effect
     * */
    fun setCooldown(entity: LivingEntity, effect: Effect, level: Int) {
        val effectMap = cooldown[entity.uniqueId]

        if(effectMap == null) {
            cooldown[entity.uniqueId] =
                mutableMapOf(effect.name to MinecraftServer.currentTick + effect.getCooldown(level))
        }
        else {
            effectMap[effect.name] = MinecraftServer.currentTick + effect.getCooldown(level)
            cooldown[entity.uniqueId] = effectMap
        }
    }

}