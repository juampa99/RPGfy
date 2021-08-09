package git.juampa99.rpgfy.item.builder.service

import git.juampa99.rpgfy.item.effect.service.EffectService
import git.juampa99.rpgfy.item.builder.entity.Item
import git.juampa99.rpgfy.item.builder.entity.armor.ArmorPiece
import git.juampa99.rpgfy.item.builder.entity.weapon.Weapon
import git.juampa99.rpgfy.item.builder.util.constants.AttributeStrings
import git.juampa99.rpgfy.item.effect.entity.Effect
import git.juampa99.rpgfy.utils.string.ColorCodes
import git.juampa99.rpgfy.utils.string.FormatCodes
import git.juampa99.rpgfy.utils.string.capitalizeFirst
import net.minecraft.server.v1_16_R3.MinecraftServer
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

    /**
     * Generates a description using the effects of an item
     * */
    private fun generateLoreText(effects: List<Pair<Effect, Int>>): List<String> {
        return effects.toList().flatMap{ el ->
            val title = ColorCodes.DARK_RED + FormatCodes.BOLD + "----------- " + FormatCodes.UNDERLINE +
                    el.first.name.lowercase().capitalizeFirst() + FormatCodes.RESET +
                    FormatCodes.BOLD + ColorCodes.DARK_RED + " -----------"
            val levelText = ColorCodes.RED + FormatCodes.BOLD + "Level: " + FormatCodes.RESET +
                    el.second.toString()
            val cooldownText = ColorCodes.YELLOW + FormatCodes.BOLD + "Cooldown: " + FormatCodes.RESET +
                    el.first.cooldown(el.second)/MinecraftServer.TPS + "s"
            val durationText = if(el.first.isDebuff())
                ColorCodes.AQUA + FormatCodes.BOLD + "Duration: " + FormatCodes.RESET +
                        (el.first.duration(el.second)/MinecraftServer.TPS).toString() + "s"
            else null
            val description = el.first.description().split("\n")
                                 .map { str -> ColorCodes.DARK_AQUA + str}

            listOfNotNull(
                title,
                levelText,
                cooldownText,
                durationText
            ) + description
        }
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

        addLore(item, generateLoreText(weapon.effects))

        return addEffects(item, weapon.effects)
    }

    private fun createArmorPiece(armorPiece: ArmorPiece): ItemStack {
        val item: ItemStack = createBasicItem(armorPiece.name, armorPiece.lore, armorPiece.type)

        addAttribute(item, armorPiece.armor, AttributeStrings.Item.ARMOR,
            Attribute.GENERIC_ARMOR, armorPiece.slot)
        addAttribute(item, armorPiece.armorToughness, AttributeStrings.Item.ARMOR_THOUGHNESS,
            Attribute.GENERIC_ARMOR_TOUGHNESS, armorPiece.slot)

        addLore(item, generateLoreText(armorPiece.effects))

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