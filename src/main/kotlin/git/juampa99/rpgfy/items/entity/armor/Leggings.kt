package git.juampa99.rpgfy.items.entity.armor

import git.juampa99.rpgfy.items.util.constants.ItemType
import org.bukkit.inventory.EquipmentSlot

class Leggings(name: String, armor: Double = 0.0,
               armorToughness: Double = 0.0, lore: List<String> = emptyList())
    : ArmorPiece(name, armor, armorToughness, lore, ItemType.LEGGINGS, EquipmentSlot.LEGS)