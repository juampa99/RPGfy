package git.juampa99.rpgfy.item.custom.builder

import git.juampa99.rpgfy.item.builder.entity.Item
import git.juampa99.rpgfy.item.builder.entity.armor.*
import git.juampa99.rpgfy.item.builder.entity.weapon.Sword
import git.juampa99.rpgfy.item.effect.entity.Effect
import git.juampa99.rpgfy.item.effect.registry.EffectRegistry
import git.juampa99.rpgfy.utils.string.toSlug
import git.juampa99.rpgfy.utils.ymlparser.builder.YamlBuilder
import git.juampa99.rpgfy.utils.ymlparser.entity.YamlEntity
import org.bukkit.Bukkit.getLogger
import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import java.lang.IllegalArgumentException

object ItemYamlBuilder: YamlBuilder() {

    /**
     * Reads effects from a Yaml file and parses it into a list of pairs of Effect,Int
     * @param path of item
     * @param file to extract effects from
     * @return List of pairs of <Effect, Int>, Effect
     * being an instance of Effect, and Int the level of the effect
     * */
    private fun getEffects(path: String, file: YamlConfiguration): List<Pair<Effect, Int>> {
        val configSection = file.getConfigurationSection("$path.effects") ?: return emptyList()
        val keys = configSection.getKeys(false)
        val effectList = mutableListOf<Pair<Effect, Int>>()

        for (key in keys) {
            val effectName: String = file.getString("$path.effects.$key.name") ?: continue
            val effectLevel: Int = file.getInt("$path.effects.$key.level", 1)

            val effectInstance = EffectRegistry.getEffect(effectName) ?: continue

            effectList.add(Pair(effectInstance, effectLevel))
        }

        return effectList
    }

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
            val effects: List<Pair<Effect, Int>> = getEffects("$path.$key", yamlFile)

            val itemBase: Item = when(type) {
                // Its a sword
                "Weapon" -> {
                    val weaponType = yamlFile.getString("$path.$key.weaponType") ?: continue
                    when(weaponType.replaceFirstChar { c -> c.uppercase() }) {
                        "Sword" -> {
                            val weapon = Sword("Sword")

                            weapon.name = name
                            weapon.lore = lore
                            weapon.effects = effects
                            weapon.damage = yamlFile.getDouble("$path.$key.damage", weapon.damage)
                            weapon.attackSpeed = yamlFile.getDouble("$path.$key.attackSpeed", weapon.attackSpeed)

                            weapon
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

                    armor.name = name
                    armor.lore = lore
                    armor.effects = effects
                    armor.armor =
                        yamlFile.getDouble("$path.$key.armor", armor.armor)
                    armor.armorToughness =
                        yamlFile.getDouble("$path.$key.armorToughness", armor.armorToughness)

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