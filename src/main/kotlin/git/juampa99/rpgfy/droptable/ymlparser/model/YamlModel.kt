package git.juampa99.rpgfy.droptable.ymlparser.model

import git.juampa99.rpgfy.droptable.ymlparser.entity.YamlEntity
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration

abstract class YamlModel {

    abstract fun buildEntities(path: String,
                               configSection: ConfigurationSection,
                               yamlFile: YamlConfiguration)
                                : List<YamlEntity>

}