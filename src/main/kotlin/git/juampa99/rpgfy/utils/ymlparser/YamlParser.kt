package git.juampa99.rpgfy.utils.ymlparser

import git.juampa99.rpgfy.Rpgfy
import git.juampa99.rpgfy.utils.ymlparser.entity.YamlEntity
import git.juampa99.rpgfy.utils.ymlparser.builder.YamlBuilder
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object YamlParser {

    /**
     * Parses a Yaml file to a list of YamlEntity
     * @param filePath path relative to /resources
     * @param objectPath path of the entity in the yaml file
     * @param builder entity builder, must extend YamlModel
     * */
    fun parseYmlConfig(filePath: String, objectPath: String, builder: YamlBuilder): List<YamlEntity> {

        val file = File(Rpgfy.plugin?.dataFolder, filePath)

        if(!file.exists()) {
            file.parentFile.mkdirs()
            Rpgfy.plugin?.saveResource(filePath, false) ?: return emptyList()
        }

        val yamlFile = YamlConfiguration.loadConfiguration(file)
        val configSection = yamlFile.getConfigurationSection(objectPath) ?: return emptyList()

        return builder.buildEntities(objectPath, configSection, yamlFile)
    }

}