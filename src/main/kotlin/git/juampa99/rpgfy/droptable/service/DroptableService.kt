package git.juampa99.rpgfy.droptable.service

import git.juampa99.rpgfy.droptable.entity.DropTableEntry
import git.juampa99.rpgfy.item.builder.entity.Item
import git.juampa99.rpgfy.item.builder.service.ItemBuilder
import git.juampa99.rpgfy.item.custom.registry.CustomItemRegistry
import git.juampa99.rpgfy.utils.string.toSlug
import org.bukkit.Bukkit.getLogger
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.lang.IllegalArgumentException
import kotlin.random.Random

object DroptableService {

    private val innerDroptable: MutableMap<String, List<DropTableEntry>> = mutableMapOf()

    // Min positive decimal for double values
    private const val MIN_ROLL = 0.0000000000000001
    private const val MAX_ROLL = 100.0

    /**
     * Takes an entity name, and randomly returns a list of drops
     * @param entityName name of the entity to drop
     * @return list of dropped items, could be empty if rolls are low
     * */
    fun rollDrops(entityName: String): List<ItemStack> {
        val list = mutableListOf<ItemStack>()
        val droptable = getDropsFromEntity(entityName.toSlug())

        for(item in droptable) {
            val roll = Random.nextDouble(MIN_ROLL, MAX_ROLL)

            if(roll <= item.dropChance) {
                val itemEntity: Item
                val itemAmount = Random.nextInt(item.minQuantity, item.maxQuantity)

                itemEntity =
                if(CustomItemRegistry.itemExists(item.itemType))
                    CustomItemRegistry.getItem(item.itemType) ?: continue
                else {
                    val itemMaterial = try { Material.valueOf(item.itemType.toSlug()) }
                                       catch(e: IllegalArgumentException){ continue }
                    Item(item.itemName, listOf(""), itemMaterial, itemAmount)
                }

                itemEntity.quantity = itemAmount

                list.add(ItemBuilder.createItem(itemEntity))
            }
        }

        return list
    }

    /**
     * Gets droptable entry of entity, empty list if it doesnt exist
     * @param entity to get drop table of
     * @return entity's droptable or empty list if doesnt exist
     * */
    fun getDropsFromEntity(entity: String): List<DropTableEntry> {
        val entitySlug = entity.toSlug()
        return innerDroptable[entitySlug] ?: emptyList()
    }

    /**
     * Adds droptable to the global droptable
     * @param droptable to merge
     * */
    fun storeDrops(droptable: MutableMap<String, List<DropTableEntry>>) {
        for(key in droptable.keys) {
            val keySlug = key.toSlug()

            innerDroptable[keySlug] =
                innerDroptable[keySlug].orEmpty() + droptable[key].orEmpty()

            if(innerDroptable[keySlug] != null)
                getLogger().info("Stored droptable for $keySlug: " + innerDroptable[keySlug])
        }
    }

}