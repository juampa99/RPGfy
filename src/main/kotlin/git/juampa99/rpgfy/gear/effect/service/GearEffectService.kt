package git.juampa99.rpgfy.gear.effect.service

import git.juampa99.rpgfy.gear.effect.entity.ArmorEffectEnum
import git.juampa99.rpgfy.gear.effect.entity.WeaponEffectEnum
import git.juampa99.rpgfy.gear.builder.util.nbt.NBTEditor
import org.bukkit.inventory.ItemStack

object GearEffectService {

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

}