package git.juampa99.rpgfy.item.service

import git.juampa99.rpgfy.item.entity.ItemPrototype
import git.juampa99.rpgfy.item.entity.armor.ArmorPiece
import git.juampa99.rpgfy.item.entity.weapon.Weapon
import git.juampa99.rpgfy.item.util.constants.AttributeStrings
import net.minecraft.server.v1_16_R3.NBTTagCompound
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.io.InvalidClassException
import java.util.*
import kotlin.jvm.Throws

object ItemBuilder {

    class InvalidItemException(message: String) : Exception(message)

    private fun addAttribute(item: ItemStack, amount: Double, attribute: String, slot: EquipmentSlot): Unit {
        val itemMeta: ItemMeta = item.itemMeta ?: return

        val attMod =
            AttributeModifier(UUID.randomUUID(), attribute,
                amount, AttributeModifier.Operation.ADD_NUMBER, slot)

        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attMod)
        item.itemMeta = itemMeta
    }

    private fun addLore(item: ItemStack, lore: List<String>) {
        val itemMeta: ItemMeta = item.itemMeta ?: return
        itemMeta.lore?.plusAssign(lore)
        item.itemMeta = itemMeta
    }

    private fun setName(item: ItemStack, name: String) {
        val itemMeta: ItemMeta = item.itemMeta ?: return
        itemMeta.setDisplayName(name)
        item.itemMeta = itemMeta
    }

    private fun createItem(name: String, lore: List<String>, type: Material): ItemStack {
        val item = ItemStack(type, 1)

        setName(item, name)
        addLore(item, lore)

        return item
    }

    private fun createWeapon(weapon: Weapon): ItemStack {
        val item: ItemStack = createItem(weapon.name, weapon.lore, weapon.type)

        addAttribute(item, weapon.attackSpeed, AttributeStrings.Item.ATTACK_SPEED, weapon.slot)
        addAttribute(item, weapon.damage, AttributeStrings.Item.ATTACK_DAMAGE, weapon.slot)

        return item
    }

    private fun createArmorPiece(armorPiece: ArmorPiece): ItemStack {
        val item: ItemStack = createItem(armorPiece.name, armorPiece.lore, armorPiece.type)

        addAttribute(item, armorPiece.armor, AttributeStrings.Item.ARMOR, armorPiece.slot)
        addAttribute(item, armorPiece.armorToughness, AttributeStrings.Item.ARMOR, armorPiece.slot)

        return item
    }

    /**
     * Generates ItemStack based on the config specified in item
     * @param item item config
     * @throws InvalidItemException
     * @return generated item
     * */
    @Throws(InvalidItemException::class)
    fun createItem(item: ItemPrototype): ItemStack {
        return when(item) {
            is Weapon -> createWeapon(item)
            is ArmorPiece -> createArmorPiece(item)
            else -> throw InvalidItemException("No type of weapon matches input")
        }
    }
}