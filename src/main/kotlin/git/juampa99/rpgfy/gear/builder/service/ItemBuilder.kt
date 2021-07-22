package git.juampa99.rpgfy.gear.builder.service

import git.juampa99.rpgfy.gear.effect.service.EffectService
import git.juampa99.rpgfy.gear.builder.entity.GearPrototype
import git.juampa99.rpgfy.gear.builder.entity.Item
import git.juampa99.rpgfy.gear.builder.entity.armor.ArmorPiece
import git.juampa99.rpgfy.gear.builder.entity.weapon.Weapon
import git.juampa99.rpgfy.gear.builder.util.constants.AttributeStrings
import git.juampa99.rpgfy.gear.effect.entity.Effect
import git.juampa99.rpgfy.utils.string.ColorCodes
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.*
import kotlin.jvm.Throws

object ItemBuilder {

    class InvalidItemException(message: String) : Exception(message)

    private fun addAttribute(item: ItemStack, amount: Double,
                             attribute: String, attributeEnum: Attribute,
                             slot: EquipmentSlot): Unit {
        val itemMeta: ItemMeta = item.itemMeta ?: return

        val mod =
            AttributeModifier(UUID.randomUUID(), attribute,
                amount, AttributeModifier.Operation.ADD_NUMBER, slot)

        itemMeta.addAttributeModifier(attributeEnum, mod)
        item.itemMeta = itemMeta
    }

    private fun addLore(item: ItemStack, lore: List<String>) {
        val itemMeta: ItemMeta = item.itemMeta ?: return
        val oldLore = itemMeta.lore ?: emptyList()
        itemMeta.lore = listOf(lore.toMutableList(), oldLore).flatten()

        item.itemMeta = itemMeta
    }

    private fun setName(item: ItemStack, name: String) {
        val itemMeta: ItemMeta = item.itemMeta ?: return
        itemMeta.setDisplayName(name)
        item.itemMeta = itemMeta
    }

    private fun createBasicItem(name: String, lore: List<String>,
                                type: Material, quantity: Int = 1): ItemStack {
        val item = ItemStack(type, quantity)

        setName(item, name)
        addLore(item, lore)

        return item
    }

    private fun addEffects(item: ItemStack,
                           effects: List<Pair<Effect, Int>>): ItemStack {
        return EffectService.addEffects(item, effects)
    }

    private fun createWeapon(weapon: Weapon): ItemStack {
        val item: ItemStack = createBasicItem(weapon.name, weapon.lore, weapon.type)

        addAttribute(item, weapon.attackSpeed, AttributeStrings.Item.ATTACK_SPEED,
            Attribute.GENERIC_ATTACK_SPEED, weapon.slot)
        addAttribute(item, weapon.damage, AttributeStrings.Item.ATTACK_DAMAGE,
            Attribute.GENERIC_ATTACK_DAMAGE, weapon.slot)

        // Adds effects to the items lore, as "Effect Name LEVEL"
        addLore(item, weapon.effects.toList().map{ el ->
            ColorCodes.GREEN + el.first.name.lowercase().capitalize() + " " + el.second
        })

        return addEffects(item, weapon.effects)
    }

    private fun createArmorPiece(armorPiece: ArmorPiece): ItemStack {
        val item: ItemStack = createBasicItem(armorPiece.name, armorPiece.lore, armorPiece.type)

        addAttribute(item, armorPiece.armor, AttributeStrings.Item.ARMOR,
            Attribute.GENERIC_ARMOR, armorPiece.slot)
        addAttribute(item, armorPiece.armorToughness, AttributeStrings.Item.ARMOR_THOUGHNESS,
            Attribute.GENERIC_ARMOR_TOUGHNESS, armorPiece.slot)

        // Adds effects to the items lore, as "Effect Name LEVEL"
        addLore(item, armorPiece.effects.toList().map{ el ->
            ColorCodes.GREEN + el.first.name.lowercase().capitalize() + " " + el.second
        })

        return addEffects(item, armorPiece.effects)
    }

    /**
     * Generates ItemStack based on the config specified in item
     * @param gear item config
     * @throws InvalidItemException
     * @return generated item
     * */
    @Throws(InvalidItemException::class)
    fun createItem(gear: Item): ItemStack {
        return when(gear) {
            is Weapon -> createWeapon(gear)
            is ArmorPiece -> createArmorPiece(gear)
            else -> createBasicItem(gear.name, gear.lore, gear.type, gear.quantity)
        }
    }
}