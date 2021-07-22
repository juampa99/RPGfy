package git.juampa99.rpgfy.utils.ymlparser.builder

import git.juampa99.rpgfy.utils.ymlparser.entity.YamlEntity
import org.bukkit.Bukkit
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration

abstract class YamlBuilder {

    abstract fun buildEntities(path: String,
                               configSection: ConfigurationSection,
                               yamlFile: YamlConfiguration)
                                : List<YamlEntity>

    fun getDoubleParameter(yamlFile: YamlConfiguration, path: String): Double? {
        var value: Double? = null

        try {
            value = yamlFile.getDouble(path)
        }
        catch(e: Exception) {
            Bukkit.getLogger().warning("An error ocurred while parsing $path on ${yamlFile.name}")
        }

        return value
    }

    fun getStringParameter(yamlFile: YamlConfiguration, path: String): String? {
        var value: String? = null

        try {
            value = yamlFile.getString(path)
        }
        catch(e: Exception) {
            Bukkit.getLogger().warning("An error ocurred while parsing $path on ${yamlFile.name}")
        }

        return value
    }

}