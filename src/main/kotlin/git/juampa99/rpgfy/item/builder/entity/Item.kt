package git.juampa99.rpgfy.item.builder.entity

import git.juampa99.rpgfy.utils.ymlparser.entity.YamlEntity
import org.bukkit.Material

open class Item(
    var name: String, var lore: List<String>,
    var type: Material, var quantity: Int
): YamlEntity {
    override fun toString(): String {
        return "$name, $type ($quantity)"
    }
}