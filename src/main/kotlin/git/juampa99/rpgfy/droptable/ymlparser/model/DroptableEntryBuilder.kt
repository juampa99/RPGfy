package git.juampa99.rpgfy.droptable.ymlparser.model

import git.juampa99.rpgfy.droptable.ymlparser.entity.DropTableEntry
import git.juampa99.rpgfy.droptable.ymlparser.entity.YamlEntity
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration

object DroptableEntryBuilder : YamlModel() {

        override fun buildEntities(path: String,
                                   configSection: ConfigurationSection,
                                    yamlFile: YamlConfiguration)
                                    : List<YamlEntity> {

            val entityList = mutableListOf<YamlEntity>()
            val configSectionKeys = configSection.getKeys(false)

            for (key in configSectionKeys) {
               val name = yamlFile.getString("$path.$key.name") ?: continue
               val dropChance = yamlFile.getString("$path.$key.dropchance") ?: continue
               // Quantity defaults to 1 if not specified in the Yaml file
               val quantity = yamlFile.getInt("$path.$key.quantity", 1)

               entityList.add(DropTableEntry(name, dropChance.toDouble(), quantity))
            }

            return entityList
        }

}