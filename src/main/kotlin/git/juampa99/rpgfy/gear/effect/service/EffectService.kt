package git.juampa99.rpgfy.gear.effect.service

import git.juampa99.rpgfy.Rpgfy
import git.juampa99.rpgfy.gear.effect.entity.ArmorEffectEnum
import git.juampa99.rpgfy.gear.effect.entity.WeaponEffectEnum
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
     * Creates a stack with the specified effects. DOESNT MODIFY THE itemStack, RETURNS A NEW INSTANCE
     * */
    @JvmName("addEffectsWeapon")
    fun addEffects(itemStack: ItemStack, effects: Map<WeaponEffectEnum, Int>): ItemStack {
        val parsedMap = effects.mapKeys { k -> k.key.name  }
        return NBTEditor.createStackWithTags(itemStack, "effects", parsedMap)
    }

    /**
     * Creates a stack with the specified effects. DOESNT MODIFY THE itemStack, RETURNS A NEW INSTANCE
     * */
    @JvmName("addEffectsArmor")
    fun addEffects(itemStack: ItemStack, effects: Map<ArmorEffectEnum, Int>): ItemStack {
        val parsedMap = effects.mapKeys { k -> k.key.name }
        return NBTEditor.createStackWithTags(itemStack, "effects", parsedMap)
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
            // If effects doesnt exist, skip this entry
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

    /**
     * @return True if itemStack has any effect
     * */
    fun hasEffects(itemStack: ItemStack): Boolean {
        return NBTEditor.hasKey(itemStack, "effects")
    }

}