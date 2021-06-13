package git.juampa99.rpgfy.items.util

import org.bukkit.Material

interface ItemPrototype
data class Sword(val name: String, val damage: Double = 0.0,
                 val attackSpeed: Double = 1.6, val lore: List<String> = emptyList()) : ItemPrototype {
                     val type: Material = Material.DIAMOND_SWORD
                 }
data class Helmet(val name: String, val armor: Double = 0.0,
                      val armorToughness: Double = 0.0, val lore: List<String> = emptyList()) : ItemPrototype
data class Chestplate(val name: String, val armor: Double = 0.0,
                      val armorToughness: Double = 0.0, val lore: List<String> = emptyList()) : ItemPrototype
data class Leggings(val name: String, val armor: Double = 0.0,
                      val armorToughness: Double = 0.0, val lore: List<String> = emptyList()) : ItemPrototype
data class Boots(val name: String, val armor: Double = 0.0,
                      val armorToughness: Double = 0.0, val lore: List<String> = emptyList()) : ItemPrototype