package git.juampa99.rpgfy.gear.custom.builder

import git.juampa99.rpgfy.gear.builder.entity.Item
import git.juampa99.rpgfy.gear.builder.entity.armor.*
import git.juampa99.rpgfy.gear.builder.entity.weapon.Sword
import git.juampa99.rpgfy.utils.string.toSlug
import git.juampa99.rpgfy.utils.ymlparser.builder.YamlBuilder
import git.juampa99.rpgfy.utils.ymlparser.entity.YamlEntity
import org.bukkit.Bukkit.getLogger
import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import java.lang.IllegalArgumentException
import kotlin.jvm.Throws

object ItemYamlBuilder: YamlBuilder() {

    override fun buildEntities(
        path: String,
        configSection: ConfigurationSection,
        yamlFile: YamlConfiguration
    ): List<YamlEntity> {
        val itemList = mutableListOf<Item>()
        val configSectionKeys = configSection.getKeys(false)

        for (key in configSectionKeys) {
            // Non-optional values skip this item if not present
            val type = yamlFile.getString("$path.$key.type") ?: continue
            // This cannot return null, since we are passing a default value
            val name: String = yamlFile.getString("$path.$key.name", type)!!
            val lore = yamlFile.getString("$path.$key.lore", "")
                                ?.split("\n")?.map { it.trim() } ?: listOf("")

            val itemBase: Item = when(type) {
                // Its a sword
                "Weapon" -> {
                    val weaponType = yamlFile.getString("$path.$key.weaponType") ?: continue
                    when(weaponType.replaceFirstChar { c -> c.uppercase() }) {
                        "Sword" -> {
                            val item = Sword("Sword")

                            item.damage = yamlFile.getDouble("$path.$key.damage", item.damage)
                            item.attackSpeed = yamlFile.getDouble("$path.$key.attackSpeed", item.attackSpeed)
                            item.name = name
                            item.lore = lore

                            item
                        }
                        else -> continue
                    }
                }
                // Its an armor piece
                "Armor" -> {
                    val armorType = yamlFile.getString("$path.$key.armorType") ?: continue

                    val armor = when(armorType.replaceFirstChar { c->c.uppercase() }) {
                        "Helmet" -> Helmet(name)
                        "Chestplate" -> Chestplate(name)
                        "Leggings" -> Leggings(name)
                        "Boots" -> Boots(name)
                        else -> continue
                    }

                    armor.armor = yamlFile.getDouble("$path.$key.armor", armor.armor)
                    armor.armorToughness = yamlFile.getDouble("$path.$key.armorToughness", armor.armorToughness)
                    armor.lore = lore

                    armor
                }
                // Its a regular item (Type is a material)
                else -> {
                    try {
                        // If type does not match with an existing material, it will throw
                        // an IllegalArgumentException, in such case we just log it and skip the entry
                        val material = Material.valueOf(type.toSlug())
                        Item(name, lore, material, 1)
                    }
                    catch(e: IllegalArgumentException) {
                        getLogger().warning("Type does not match an existing Item Material nor an item type")
                        continue
                    }
                }
            }

            itemList.add(itemBase)
        }

        return itemList
    }

}