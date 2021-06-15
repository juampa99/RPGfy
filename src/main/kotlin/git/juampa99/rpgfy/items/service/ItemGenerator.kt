package git.juampa99.rpgfy.items.service

import git.juampa99.rpgfy.items.entity.ItemPrototype
import git.juampa99.rpgfy.items.entity.armor.ArmorPiece
import git.juampa99.rpgfy.items.entity.weapon.Sword
import git.juampa99.rpgfy.items.util.constants.AttributeStrings
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
        val item: ItemStack = generateItem(type)

        setName(item, name)
        addLore(item, lore)

        return item
    }

    private fun createSword(sword: Sword): ItemStack {
        val item: ItemStack = createItem(sword.name, sword.lore, sword.type)

        addAttribute(item, sword.attackSpeed, AttributeStrings.Item.ATTACK_SPEED, EquipmentSlot.HAND)
        addAttribute(item, sword.damage, AttributeStrings.Item.ATTACK_DAMAGE, EquipmentSlot.HAND)

        return item
    }

    private fun createArmorPiece(armorPiece: ArmorPiece): ItemStack {
        val item: ItemStack = createItem(armorPiece.name, armorPiece.lore, armorPiece.type)

        addAttribute(item, armorPiece.armor, AttributeStrings.Item.ARMOR, armorPiece.slot)
        addAttribute(item, armorPiece.armorToughness, AttributeStrings.Item.ARMOR, armorPiece.slot)

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
            is ArmorPiece -> createArmorPiece(item)
            else -> ItemStack(Material.STICK)
        }
    }
}