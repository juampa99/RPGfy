package git.juampa99.rpgfy.gear.builder.entity.armor

import git.juampa99.rpgfy.gear.builder.entity.GearPrototype
import git.juampa99.rpgfy.gear.effect.entity.ArmorEffect
import git.juampa99.rpgfy.gear.effect.entity.Effect
import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class ArmorPiece(name: String, var armor: Double,
                          var armorToughness: Double, lore: List<String> = emptyList(),
                          type: Material, slot: EquipmentSlot,
                          effects: Map<ArmorEffect, Int> = mapOf())
    : GearPrototype(name, lore, type, slot, effects.mapKeys { e -> e.key.name })
