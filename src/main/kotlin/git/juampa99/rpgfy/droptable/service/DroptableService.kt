package git.juampa99.rpgfy.droptable.service

import git.juampa99.rpgfy.droptable.ymlparser.entity.DropTableEntry
import git.juampa99.rpgfy.droptable.ymlparser.entity.YamlEntity
import org.bukkit.Bukkit.getLogger

object DroptableService {

    private val innerDroptable: MutableMap<String, List<DropTableEntry>> = mutableMapOf()

    /**
     * Gets droptable entry of entity, empty list if it doesnt exist
     * @param entity to get drop table of
     * @return entity's droptable or empty list if doesnt exist
     * */
    fun getDropsFromEntity(entity: String): List<DropTableEntry> {
        val entitySlug = entity.uppercase().replace(" ", "_")
        return innerDroptable[entitySlug] ?: emptyList()
    }

    /**
     * Adds droptable to the global droptable
     * @param droptable to merge
     * */
    fun loadDroptable(droptable: MutableMap<String, List<DropTableEntry>>) {
        for(key in droptable.keys) {
            val keySlug = key.uppercase().replace(" ", "_")

            innerDroptable[keySlug] =
                innerDroptable[keySlug].orEmpty() + droptable[key].orEmpty()

            getLogger().info("Stored droptable for $keySlug: " + innerDroptable[keySlug])
        }
    }

}