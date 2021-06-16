package git.juampa99.rpgfy.gear.entity.weapon

import git.juampa99.rpgfy.gear.entity.GearPrototype
import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class Weapon(name: String, var damage: Double,
                      var attackSpeed: Double, lore: List<String>,
                      type: Material, slot: EquipmentSlot)
    : GearPrototype(name, lore, type, slot)