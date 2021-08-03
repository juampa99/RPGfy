package git.juampa99.rpgfy.item.effect.registry

import git.juampa99.rpgfy.item.effect.entity.Effect
import git.juampa99.rpgfy.utils.string.toSlug
import org.bukkit.Bukkit.getLogger

object EffectRegistry {

    private val registry: MutableMap<String, Effect> = mutableMapOf()

    /**
     * Registers effect. Effects must be registered in order to be
     * triggered by triggerEffects
     * @param effect to register
     * */
    fun register(effect: Effect) {
        registry[effect.name.toSlug()] = effect
        getLogger().info("> Registered $effect")
    }

    /**
     * Returns effect from the effects name
     * @return Registered effect with a matching name
     * */
    fun getEffect(effectName: String): Effect? {
        return registry[effectName.toSlug()]
    }

    /**
     * @return Returns a list of all effects
     * */
    fun getAllEffects(): List<Effect> {
        return registry.toList().map { el -> el.second }
    }

}