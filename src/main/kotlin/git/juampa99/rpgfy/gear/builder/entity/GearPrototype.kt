package git.juampa99.rpgfy.gear.builder.entity

import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class GearPrototype(var name: String, var lore: List<String>, val type: Material, val slot: EquipmentSlot)


