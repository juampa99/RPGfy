package git.juampa99.rpgfy.gear.custom.service

import git.juampa99.rpgfy.gear.builder.entity.Item
import org.bukkit.Bukkit

object CustomItemService {

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
     * @return true if item exists, false otherwise
     * */
    fun itemExists(itemName: String): Boolean {
        return items.find { it.name == itemName } != null
    }

    /**
     * Adds custom item to item "database"
     * @param droptable to merge
     * */
    fun storeItem(item: Item) {
        items.add(item)
        Bukkit.getLogger().info("Stored item ${item.name}")
    }

    fun storeItems(items: List<Item>) {
        for(item in items) storeItem(item)
    }

}