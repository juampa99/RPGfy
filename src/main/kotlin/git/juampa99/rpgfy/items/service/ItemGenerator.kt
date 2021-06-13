package git.juampa99.rpgfy.items.service

import git.juampa99.rpgfy.items.util.ItemPrototype
import git.juampa99.rpgfy.items.util.Sword
import git.juampa99.rpgfy.items.util.constants.AttributeStrings
import git.juampa99.rpgfy.items.util.constants.ItemType
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.*

object ItemGenerator {

    private fun generateItem(type: Material): ItemStack {
        return ItemStack(type, 1)
    }

    private fun addAttribute(item: ItemStack, amount: Double, attribute: String, slot: EquipmentSlot): Unit {
        val itemMeta: ItemMeta = item.itemMeta ?: return

        val attMod: AttributeModifier =
            AttributeModifier(
                UUID.randomUUID(), attribute,
                amount, AttributeModifier.Operation.ADD_NUMBER, slot)

        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attMod)
        item.itemMeta = itemMeta
    }

    private fun addLore(item: ItemStack, lore: String) {
        val itemMeta: ItemMeta = item.itemMeta ?: return
        itemMeta.lore = listOf(lore)
        item.itemMeta = itemMeta
    }

    private fun setName(item: ItemStack, name: String) {
        val itemMeta: ItemMeta = item.itemMeta ?: return
        itemMeta.setDisplayName(name)
        item.itemMeta = itemMeta
    }

    private fun createSword(sword: Sword): ItemStack {
        val item: ItemStack = generateItem(ItemType.SWORD)

        setName(item, sword.name)
        addLore(item, sword.lore)
        addAttribute(item, sword.attackSpeed, AttributeStrings.Item.ATTACK_SPEED, EquipmentSlot.HAND)
        addAttribute(item, sword.damage, AttributeStrings.Item.ATTACK_DAMAGE, EquipmentSlot.HAND)

        return item
    }

    /**
     * Generates item based on the config specified in item
     * @param item item config
     * @return generated item
     * */
    fun createItem(item: ItemPrototype): ItemStack {
        return when(item) {
            is Sword -> createSword(item)
            else -> ItemStack(Material.STICK)
        }
    }
}