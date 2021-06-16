package git.juampa99.rpgfy.gear.util.nbt

import net.minecraft.server.v1_16_R3.*
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

object NBTEditor {

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
     * Creates a sack using the parameters. DOESNT MODIFY THE itemStack PARAMETER, RETURNS A NEW INSTANCE
     * */
    @JvmName("addTagToStackString")
    fun createStackWithTags(itemStack: ItemStack, tagName: String, tags: Map<String, String>): ItemStack {
        val newComp = NBTTagCompound()

        // This "a" method creates a new instance of a NBTTagString
        tags.forEach { (k, v) -> newComp.set(k, NBTTagString.a(v)) }

        return newStackFrom(itemStack, newComp, tagName)
    }

    /**
     * Creates a sack using the parameters. DOESNT MODIFY THE itemStack PARAMETER, RETURNS A NEW INSTANCE
     * */
    @JvmName("addTagToStackInt")
    fun createStackWithTags(itemStack: ItemStack, tagName: String, tags: Map<String, Int>): ItemStack {
        val newComp = NBTTagCompound()

        // This "a" method creates a new instance of a NBTTagString
        tags.forEach { (k, v) -> newComp.set(k, NBTTagInt.a(v)) }

        return newStackFrom(itemStack, newComp, tagName)
    }

    /**
     * Creates a sack using the parameters. DOESNT MODIFY THE itemStack PARAMETER, RETURNS A NEW INSTANCE
     * */
    @JvmName("addTagToStackIntArray")
    fun createStackWithTags(itemStack: ItemStack, tagName: String, tags: Map<String, IntArray>): ItemStack {
        val newComp = NBTTagCompound()

        tags.forEach { (k, v) -> newComp.set(k, NBTTagIntArray(v)) }

        return newStackFrom(itemStack, newComp, tagName)
    }

}