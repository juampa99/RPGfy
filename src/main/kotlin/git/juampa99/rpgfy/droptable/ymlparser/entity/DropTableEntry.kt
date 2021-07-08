package git.juampa99.rpgfy.droptable.ymlparser.entity

data class DropTableEntry(val itemName: String, val dropChance: Double, val minQuantity: Int = 0,
                          val maxQuantity: Int = 1) : YamlEntity {
    override fun toString(): String {
        return "$itemName ($minQuantity-$maxQuantity): $dropChance"
    }
}