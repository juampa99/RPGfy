package git.juampa99.rpgfy.items.util

interface ItemPrototype
data class Sword(val name: String, val damage: Double = 0.0,
                 val attackSpeed: Double = 1.6, val lore: String = "") : ItemPrototype
data class ArmorPiece(val name: String, val armor: Double = 0.0,
                      val armorToughness: Double = 0.0, val lore: String = "") : ItemPrototype
