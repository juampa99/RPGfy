package git.juampa99.rpgfy.droptable.entity

import git.juampa99.rpgfy.utils.ymlparser.entity.YamlEntity

data class DropTableEntry(val itemName: String, val dropChance: Double, val minQuantity: Int = 0,
                          val maxQuantity: Int = 1) : YamlEntity {
    override fun toString(): String {
        return "$itemName ($minQuantity-$maxQuantity): $dropChance"
    }
}