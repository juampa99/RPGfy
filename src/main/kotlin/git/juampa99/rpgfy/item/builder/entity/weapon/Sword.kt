package git.juampa99.rpgfy.item.builder.entity.weapon

import git.juampa99.rpgfy.item.builder.util.constants.ItemType
import git.juampa99.rpgfy.item.effect.entity.WeaponEffect
import org.bukkit.inventory.EquipmentSlot

class Sword(name: String, damage: Double = 1.0,
            attackSpeed: Double = 1.6, lore: List<String> = emptyList(),
            effects: List<Pair<WeaponEffect, Int>> = emptyList())
    : Weapon(name, damage, attackSpeed, lore,
            ItemType.SWORD, EquipmentSlot.HAND, effects)