package git.juampa99.rpgfy.gear.entity.armor

import git.juampa99.rpgfy.gear.util.constants.ItemType
import org.bukkit.inventory.EquipmentSlot

open class Helmet(name: String, armor: Double = 0.0,
             armorToughness: Double = 0.0, lore: List<String> = emptyList())
    : ArmorPiece(name, armor, armorToughness, lore, ItemType.HELMET, EquipmentSlot.HEAD)