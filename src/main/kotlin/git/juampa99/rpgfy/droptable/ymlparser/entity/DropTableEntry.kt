package git.juampa99.rpgfy.droptable.ymlparser.entity

data class DropTableEntry(val itemName: String, val dropChance: Double, val quantity: Int = 1 ) : YamlEntity {
    override fun toString(): String {
        return "$itemName ($quantity): $dropChance"
    }
}