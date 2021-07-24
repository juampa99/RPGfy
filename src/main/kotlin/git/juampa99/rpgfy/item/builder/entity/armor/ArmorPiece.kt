package git.juampa99.rpgfy.item.builder.entity.armor

import git.juampa99.rpgfy.item.builder.entity.GearPrototype
import git.juampa99.rpgfy.item.effect.entity.ArmorEffect
import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class ArmorPiece(name: String, var armor: Double,
                          var armorToughness: Double, lore: List<String> = emptyList(),
                          type: Material, slot: EquipmentSlot,
                          effects: List<Pair<ArmorEffect, Int>> = emptyList())
    : GearPrototype(name, lore, type, slot, effects)
