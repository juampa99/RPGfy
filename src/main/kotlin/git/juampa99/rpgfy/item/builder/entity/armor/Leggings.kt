package git.juampa99.rpgfy.item.builder.entity.armor

import git.juampa99.rpgfy.item.builder.util.constants.ItemType
import git.juampa99.rpgfy.item.effect.entity.ArmorEffect
import org.bukkit.inventory.EquipmentSlot

open class Leggings(name: String, armor: Double = 0.0,
                    armorToughness: Double = 0.0,
                    lore: List<String> = emptyList(),
                    effects: List<Pair<ArmorEffect, Int>> = emptyList())
    : ArmorPiece(name, armor, armorToughness, lore,
                 ItemType.LEGGINGS, EquipmentSlot.LEGS, effects)