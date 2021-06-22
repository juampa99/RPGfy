package git.juampa99.rpgfy.gear.builder.entity.weapon

import git.juampa99.rpgfy.gear.builder.entity.GearPrototype
import git.juampa99.rpgfy.gear.effect.entity.Effect
import git.juampa99.rpgfy.gear.effect.entity.WeaponEffect
import org.bukkit.Material
import org.bukkit.inventory.EquipmentSlot

abstract class Weapon(name: String, var damage: Double,
                      var attackSpeed: Double, lore: List<String> = listOf(),
                      type: Material, slot: EquipmentSlot,
                      effects: List<Pair<WeaponEffect, Int>> = emptyList())
    : GearPrototype(name, lore, type, slot, effects)