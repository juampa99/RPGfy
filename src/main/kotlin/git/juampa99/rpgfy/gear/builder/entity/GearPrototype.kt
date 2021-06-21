package git.juampa99.rpgfy.gear.builder.entity

import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class GearPrototype(
    var name: String, var lore: List<String>,
    val type: Material, val slot: EquipmentSlot,
    // Effects may be better represented as List<Pair<Effect, Int>>
    // so it will be polymorphic and will make more sense
    var effects: Map<String, Int>
)
