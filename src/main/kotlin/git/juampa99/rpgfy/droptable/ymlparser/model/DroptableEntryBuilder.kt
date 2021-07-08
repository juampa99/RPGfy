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
               // minQuantity defaults to 0 and maxQuantity to 1 if values not provided
               val minQuantity = yamlFile.getInt("$path.$key.minQuantity", 0)
               val maxQuantity = yamlFile.getInt("$path.$key.maxQuantity", 1)

               entityList.add(DropTableEntry(name, dropChance.toDouble(), minQuantity, maxQuantity))
            }

            return entityList
        }

}