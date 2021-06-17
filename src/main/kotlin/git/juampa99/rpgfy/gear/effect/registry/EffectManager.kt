package git.juampa99.rpgfy.gear.effect.registry

import git.juampa99.rpgfy.gear.effect.entity.Effect
import org.bukkit.inventory.ItemStack

object EffectManager {

    private val registry: MutableMap<String, Effect> = mutableMapOf()

    fun register(effectName: String, effect: Effect) {
        registry[effectName] = effect
    }

    fun getEffect(effectName: String): Effect? = registry[effectName]

    // Needs implementation
    fun triggerEffects(item: ItemStack) {
        if(!hasEffects(item)) return
    }

    // Needs implementation
    fun hasEffects(itemStack: ItemStack): Boolean {
        return false
    }

}