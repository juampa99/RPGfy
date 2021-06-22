package git.juampa99.rpgfy.gear.builder.entity

import git.juampa99.rpgfy.gear.effect.entity.Effect
import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class GearPrototype(
    var name: String, var lore: List<String>,
    val type: Material, val slot: EquipmentSlot,
    var effects: List<Pair<Effect, Int>>
)
