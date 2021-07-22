package git.juampa99.rpgfy.droptable.builder

import git.juampa99.rpgfy.droptable.entity.DropTableEntry
import git.juampa99.rpgfy.utils.ymlparser.entity.YamlEntity
import git.juampa99.rpgfy.utils.ymlparser.builder.YamlBuilder
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration

object DroptableEntryBuilder : YamlBuilder() {

        override fun buildEntities(path: String,
                                   configSection: ConfigurationSection,
                                    yamlFile: YamlConfiguration)
                                    : List<YamlEntity> {

            val entityList = mutableListOf<YamlEntity>()
            val configSectionKeys = configSection.getKeys(false)

            for (key in configSectionKeys) {
               val type = yamlFile.getString("$path.$key.type") ?: continue
               val name = yamlFile.getString("$path.$key.name", type)!!
               // Dropchance must be provided, otherwise it skips the entry
               val dropChance = yamlFile.getString("$path.$key.dropchance")?.toDouble() ?: continue
               // minQuantity defaults to 0 and maxQuantity to 1 if values not provided
               var minQuantity = yamlFile.getInt("$path.$key.minQuantity", 0)
               var maxQuantity = yamlFile.getInt("$path.$key.maxQuantity", 1)

               // Stack sizes are capped at 64
               if(minQuantity > 64) minQuantity = 64
               if(maxQuantity > 64) maxQuantity = 64

               entityList.add(DropTableEntry(name, type, dropChance, minQuantity, maxQuantity))
            }

            return entityList
        }

}