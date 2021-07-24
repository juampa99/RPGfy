package git.juampa99.rpgfy.item.builder.entity

import git.juampa99.rpgfy.item.effect.entity.Effect
import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

open class GearPrototype(
    name: String, lore: List<String>,
    type: Material, val slot: EquipmentSlot,
    var effects: List<Pair<Effect, Int>>
): Item(name, lore, type, 1)
