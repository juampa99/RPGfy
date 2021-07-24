package git.juampa99.rpgfy.item.builder.entity.weapon

import git.juampa99.rpgfy.item.builder.entity.GearPrototype
import git.juampa99.rpgfy.item.effect.entity.WeaponEffect
import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class Weapon(name: String, var damage: Double,
                      var attackSpeed: Double, lore: List<String> = listOf(),
                      type: Material, slot: EquipmentSlot,
                      effects: List<Pair<WeaponEffect, Int>> = emptyList())
    : GearPrototype(name, lore, type, slot, effects)