package git.juampa99.rpgfy.items.entity

import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class ItemPrototype(var name: String, var lore: List<String>, val type: Material, val slot: EquipmentSlot)


