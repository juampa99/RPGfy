package git.juampa99.rpgfy.utils.nbt

import net.minecraft.server.v1_16_R3.*
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

object NBTEditor {

    /**
     * Returns a new stack with the added compound in the tag named tagName
     * @param itemStack
     * @param newComp
     * @param tagName name of the tag to store the compound on
     * */
    private fun newStackFrom(itemStack: ItemStack, newComp: NBTTagCompound, tagName: String): ItemStack {
        val nmsStack = CraftItemStack.asNMSCopy(itemStack)
        val tagList = NBTTagList()
        val oldCompound = nmsStack.tag ?: NBTTagCompound()

        tagList.add(newComp)
        oldCompound.set(tagName, tagList)
        nmsStack.tag = oldCompound

        return CraftItemStack.asBukkitCopy(nmsStack)
    }

    /**
     * Parses nbtBase of the form {key:value} tu Pairs
     * @param nbtBase NBTBase of the form {key:value}
     * @return parsed NBTBase to Pair<String, Int>
     * */
    private fun keyValueToPair(nbtBase: NBTBase): Pair<String, String> {
        val nbtString = nbtBase.asString()
        val key = nbtString.substringAfter("{").substringBefore(":")
        val value = nbtString.substringBefore("}").substringAfter(":")

        return Pair(key, value)
    }

    /**
     * Parses a NBTTagList to a Map<String, String>
     * @param list to parse
     * @return parsed Map of the form Map<String, String>
     * */
    private fun parseNbtTagList(list: NBTTagList): Map<String, String> {
        // Associate method transforms a List of Pair<k, v> to a Map<k, v>
        return list.associate { elem -> keyValueToPair(elem) }
    }

    fun hasKey(itemStack: ItemStack, key: String): Boolean {
        val nmsStack = CraftItemStack.asNMSCopy(itemStack)
        val tag = nmsStack.tag ?: return false
        return tag.hasKey(key)
    }

    /**
     * Checks if a given key exists in an items tag
     * @param itemStack item to check
     * @param tagName name of the tag that contains the key
     * @param key to check for
     * @return true if the key exists, false if item has no tags or if the key doesnt exist
     * */
    fun hasKeyOnTag(itemStack: ItemStack, tagName: String, key: String): Boolean {
        // If the tag doesnt exist, the key doesnt exist either
        if(!hasKey(itemStack, tagName)) return false
        val nmsStack = CraftItemStack.asNMSCopy(itemStack)
        val tag = nmsStack.tag ?: return false
        // getList needs an identifier to cast the result to, 10 is the id for Compound,
        // see the spigot forum for more details
        val compoundIntIdentifier = 10
        val compound = tag.getList(tagName, compoundIntIdentifier)
        val parsedMap = parseNbtTagList(compound)

        return parsedMap.containsKey(key)
    }

    fun getKeysOnTag(itemStack: ItemStack, tagName: String): Map<String, String> {
        val nmsStack = CraftItemStack.asNMSCopy(itemStack)
        val tag = nmsStack.tag ?: return mutableMapOf()
        // getList needs an identifier to cast the result to, 10 is the id for Compound,
        // see the spigot forum for more details
        val compoundIntIdentifier = 10
        val compound = tag.getList(tagName, compoundIntIdentifier)

        return parseNbtTagList(compound)
    }

    /**
     * Creates a stack using the parameters. DOESNT MODIFY THE itemStack PARAMETER IT RETURNS A NEW INSTANCE
     * */
    @JvmName("addTagToStackString")
    fun createStackWithTags(itemStack: ItemStack, tagName: String,
                            tags: List<Pair<String, String>>): ItemStack {
        val newComp = NBTTagCompound()

        // This "a" method creates a new instance of a NBTTagString
        tags.forEach { (k, v) -> newComp.set(k, NBTTagString.a(v)) }

        return newStackFrom(itemStack, newComp, tagName)
    }

    /**
     * Creates a stack using the parameters. DOESNT MODIFY THE itemStack PARAMETER, RETURNS A NEW INSTANCE
     * */
    @JvmName("addTagToStackInt")
    fun createStackWithTags(itemStack: ItemStack, tagName: String,
                            tags: List<Pair<String, Int>>): ItemStack {
        val newComp = NBTTagCompound()

        // This "a" method creates a new instance of a NBTTagString
        tags.forEach { (k, v) -> newComp.set(k, NBTTagInt.a(v)) }

        return newStackFrom(itemStack, newComp, tagName)
    }

    /**
     * Creates a stack using the parameters. DOESNT MODIFY THE itemStack PARAMETER, RETURNS A NEW INSTANCE
     * */
    @JvmName("addTagToStackIntArray")
    fun createStackWithTags(itemStack: ItemStack, tagName: String,
                            tags: List<Pair<String, IntArray>>): ItemStack {
        val newComp = NBTTagCompound()

        tags.forEach { (k, v) -> newComp.set(k, NBTTagIntArray(v)) }

        return newStackFrom(itemStack, newComp, tagName)
    }

}