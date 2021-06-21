package git.juampa99.rpgfy.gear.effect.service

import git.juampa99.rpgfy.Rpgfy
import git.juampa99.rpgfy.gear.effect.entity.*
import git.juampa99.rpgfy.gear.effect.entity.impl.SlownessEffect
import git.juampa99.rpgfy.gear.effect.event.EffectFadeEvent
import git.juampa99.rpgfy.gear.effect.event.EffectTriggerEvent
import git.juampa99.rpgfy.gear.nbt.NBTEditor
import git.juampa99.rpgfy.gear.effect.registry.EffectRegistry
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getLogger
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack

object EffectService {

    /**
     * @return True if itemStack has any effect
     * */
    fun hasEffects(itemStack: ItemStack): Boolean {
        return NBTEditor.hasKey(itemStack, "effects")
    }

    /**
     * Creates a stack with the specified effects. DOESNT MODIFY THE itemStack, RETURNS A NEW INSTANCE
     * */
    fun addEffects(itemStack: ItemStack, effects: Map<String, Int>): ItemStack {
        return NBTEditor.createStackWithTags(itemStack, "effects", effects)
    }

    /**
     * Triggers effects of item on the target,
     * @param item Item to trigger effect
     * @param emisor Effect triggerer
     * @param target Entity to trigger effects on
     * */
    fun triggerEffects(item: ItemStack, emisor: LivingEntity, target: LivingEntity) {
        if(!hasEffects(item)) return
        val effects = NBTEditor.getKeysOnTag(item, "effects")
        val parsedEffects = effects.mapValues { value -> value.value.toInt() }

        // Trigger each effect if its registered
        parsedEffects.forEach { effect ->
            // If effect doesnt exist, skip this entry
            val effectInstance = EffectRegistry.getEffect(effect.key) ?: return@forEach
            val effectDuration = effectInstance.getDuration(effect.value)

            Bukkit.getPluginManager().callEvent(EffectTriggerEvent(target, effectInstance))
            // Schedule effect fade event to fire when the effect runs out
            Rpgfy.plugin?.let {
                Bukkit.getScheduler().scheduleSyncDelayedTask(it, {
                    Bukkit.getPluginManager().callEvent(EffectFadeEvent(target, effectInstance))
                }, effectDuration.toLong())
            }

            effectInstance.trigger(emisor, target, effect.value)
        }
    }


}