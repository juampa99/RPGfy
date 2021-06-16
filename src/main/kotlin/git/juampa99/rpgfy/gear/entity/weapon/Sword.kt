package git.juampa99.rpgfy.gear.entity.weapon

import git.juampa99.rpgfy.gear.util.constants.ItemType
import org.bukkit.inventory.EquipmentSlot

class Sword(name: String, damage: Double = 1.0,
            attackSpeed: Double = 1.6, lore: List<String> = emptyList())
    : Weapon(name, damage, attackSpeed, lore, ItemType.SWORD , EquipmentSlot.HAND)