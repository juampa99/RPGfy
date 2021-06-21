package git.juampa99.rpgfy.gear.builder.entity.armor

import git.juampa99.rpgfy.gear.builder.util.constants.ItemType
import git.juampa99.rpgfy.gear.effect.entity.ArmorEffect
import org.bukkit.inventory.EquipmentSlot

open class Chestplate(name: String, armor: Double = 0.0,
                      armorToughness: Double = 0.0,
                      lore: List<String> = emptyList(),
                      effects: Map<ArmorEffect, Int> = mapOf())
    : ArmorPiece(name, armor, armorToughness, lore,
                 ItemType.CHESTPLATE, EquipmentSlot.CHEST, effects)