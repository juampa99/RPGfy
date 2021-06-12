package git.juampa99.rpgfy.command

import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.*

class MightySwordCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) return false

        val sword: ItemStack = ItemStack(Material.DIAMOND_SWORD, 1)
        val swordMeta: ItemMeta? = sword.itemMeta

        val attMod: AttributeModifier =
            AttributeModifier(UUID.randomUUID(), "generic.attack_damage",
                10.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND )
        swordMeta?.setDisplayName("Mighty Sword")
        swordMeta?.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attMod)
        sword.itemMeta = swordMeta

        sender.inventory.addItem(sword)

        return true
    }


}