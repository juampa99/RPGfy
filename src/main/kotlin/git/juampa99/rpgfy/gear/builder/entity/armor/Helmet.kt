package git.juampa99.rpgfy.gear.builder.entity.armor

import git.juampa99.rpgfy.gear.builder.util.constants.ItemType
import git.juampa99.rpgfy.gear.effect.entity.ArmorEffect
import git.juampa99.rpgfy.gear.effect.entity.Effect
import org.bukkit.inventory.EquipmentSlot

open class Helmet(name: String, armor: Double = 0.0,
                  armorToughness: Double = 0.0,
                  lore: List<String> = emptyList(),
                  effects: List<Pair<ArmorEffect, Int>> = emptyList())
    : ArmorPiece(name, armor, armorToughness, lore,
                 ItemType.HELMET, EquipmentSlot.HEAD, effects)