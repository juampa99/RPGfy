package git.juampa99.rpgfy.gear.effect.registry

import git.juampa99.rpgfy.gear.builder.util.nbt.NBTEditor
import git.juampa99.rpgfy.gear.builder.util.nbt.NBTEditor.getKeysOnTag
import git.juampa99.rpgfy.gear.effect.entity.Effect
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack

object EffectManager {

    private val registry: MutableMap<String, Effect> = mutableMapOf()

    /**
     * Registers effect. Effects must be registered in order to be
     * triggered by triggerEffects
     * @param effect to register
     * */
    fun register(effect: Effect) {
        registry[effect.name] = effect
    }

    /**
     * Triggers effects of item on the target,
     * @param item Item to trigger effect
     * @param emisor Effect triggerer
     * @param target Entity to trigger effects on
     * */
    fun triggerEffects(item: ItemStack, emisor: LivingEntity, target: LivingEntity) {
        if(!hasEffects(item)) return
        val effects = getKeysOnTag(item, "effects")
        val parsedEffects = effects.mapValues { value -> value.value.toInt() }

        // Trigger each effect if its registered
        parsedEffects.forEach{ effect -> registry[effect.key]?.trigger(emisor, target, effect.value) }
    }

    fun hasEffects(itemStack: ItemStack): Boolean {
        return NBTEditor.hasKey(itemStack, "effects")
    }

}