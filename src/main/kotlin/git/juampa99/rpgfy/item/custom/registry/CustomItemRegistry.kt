package git.juampa99.rpgfy.item.custom.registry

import git.juampa99.rpgfy.item.builder.entity.Item
import org.bukkit.Bukkit

object CustomItemRegistry {

    private val items: MutableList<Item> = mutableListOf()

    /**
     * Gets custom item from its name
     * @param itemName name of the item to get
     * @return Custom item if entry exists, null otherwise
     * */
    fun getItem(itemName: String): Item? {
        return items.find{ it.name == itemName}
    }

    /**
     * @return list of all items
     * */
    fun getAllItems(): List<Item> {
        return items
    }

    /**
     * @return true if item exists, false otherwise
     * */
    fun itemExists(itemName: String): Boolean {
        return items.find { it.name == itemName } != null
    }

    /**
     * Adds custom item to item "database"
     * @param droptable to merge
     * */
    fun registerItem(item: Item) {
        items.add(item)
        Bukkit.getLogger().info("Stored item ${item.name}")
    }

    fun registerItems(items: List<Item>) {
        for(item in items) registerItem(item)
    }

}