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

            loop@for (key in configSectionKeys) {
               val name = yamlFile.getString("$path.$key.name") ?: continue@loop
               val dropChance = yamlFile.getString("$path.$key.dropchance") ?: continue@loop

               entityList.add(DropTableEntry(name, dropChance.toDouble()))
            }

            return entityList
        }

}