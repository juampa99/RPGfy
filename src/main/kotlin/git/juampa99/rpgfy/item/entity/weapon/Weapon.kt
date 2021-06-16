package git.juampa99.rpgfy.item.entity.weapon

import git.juampa99.rpgfy.item.entity.ItemPrototype
import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class Weapon(name: String, var damage: Double,
                      var attackSpeed: Double, lore: List<String>,
                      type: Material, slot: EquipmentSlot)
    : ItemPrototype(name, lore, type, slot)