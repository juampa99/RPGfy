package git.juampa99.rpgfy.item.entity.armor

import git.juampa99.rpgfy.item.entity.ItemPrototype
import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class ArmorPiece(name: String, var armor: Double,
                          var armorToughness: Double, lore: List<String>,
                          type: Material, slot: EquipmentSlot)
    : ItemPrototype(name, lore, type, slot)