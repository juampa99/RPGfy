package git.juampa99.rpgfy.item.entity.armor

import git.juampa99.rpgfy.item.util.constants.ItemType
import org.bukkit.inventory.EquipmentSlot

open class Chestplate(name: String, armor: Double = 0.0,
                 armorToughness: Double = 0.0, lore: List<String> = emptyList())
    : ArmorPiece(name, armor, armorToughness, lore, ItemType.CHESTPLATE, EquipmentSlot.CHEST)