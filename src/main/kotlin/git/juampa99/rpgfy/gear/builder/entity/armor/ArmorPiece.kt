package git.juampa99.rpgfy.gear.builder.entity.armor

import git.juampa99.rpgfy.gear.builder.entity.GearPrototype
import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class ArmorPiece(name: String, var armor: Double,
                          var armorToughness: Double, lore: List<String>,
                          type: Material, slot: EquipmentSlot)
    : GearPrototype(name, lore, type, slot)