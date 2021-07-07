package git.juampa99.rpgfy.droptable.ymlparser

import git.juampa99.rpgfy.Rpgfy
import git.juampa99.rpgfy.droptable.ymlparser.entity.YamlEntity
import git.juampa99.rpgfy.droptable.ymlparser.model.YamlModel
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object YamlParser {

    /**
     * Parses a Yaml file to a list of YamlEntity
     * @param filePath path relative to /resources
     * @param objectPath path of the entity in the yaml file
     * @param model entity builder, must extend YamlModel
     * */
    fun parseYmlConfig(filePath: String, objectPath: String, model: YamlModel): List<YamlEntity> {

        val file = File(Rpgfy.plugin?.dataFolder, filePath)

        if(!file.exists()) {
            file.parentFile.mkdirs()
            Rpgfy.plugin?.saveResource(filePath, false) ?: return emptyList()
        }

        val yamlFile = YamlConfiguration.loadConfiguration(file)
        val configSection = yamlFile.getConfigurationSection(objectPath) ?: return emptyList()

        return model.buildEntities(objectPath, configSection, yamlFile)
    }

}