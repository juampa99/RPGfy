package git.juampa99.rpgfy.command

import git.juampa99.rpgfy.items.entity.ItemPrototype
import git.juampa99.rpgfy.items.entity.armor.*
import git.juampa99.rpgfy.items.service.ItemGenerator
import git.juampa99.rpgfy.items.entity.weapon.Sword
import git.juampa99.rpgfy.items.entity.weapon.Weapon
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode
import org.bukkit.Bukkit.getLogger
import org.bukkit.block.Chest
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CreateItemCommand : CommandExecutor {

    private fun createWeapon(args: Array<out String>): ItemPrototype {
        val itemName: String = args[1]
        val weapon: Weapon = Sword(itemName)

        if(args.size >= 3) {
            weapon.damage = args[2].toDouble()
        }
        if(args.size >= 4) {
            weapon.attackSpeed = args[3].toDouble()
        }

        return weapon
    }

    private fun createArmor(args: Array<out String>): ItemPrototype {
        val itemName: String = args[2]

        val armor: ArmorPiece = when(args[1].lowercase()) {
            "helmet" -> Helmet(itemName)
            "chestplate" -> Chestplate(itemName)
            "leggings" -> Leggings(itemName)
            "boots" -> Boots(itemName)
            else -> throw RuntimeException()
        }

        if(args.size >= 4) armor.armor = args[3].toDouble()
        if(args.size >= 5) armor.armorToughness = args[4].toDouble()

        return armor
    }

    /**
     * armor <type> <name> <armor?> <armor_toughness?>
     * weapon <name> <damage?> <attack_speed?
     * */
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) return false
        if(args.size < 2) return false
        getLogger().info(args[0])

        val itemPrototype: ItemPrototype

        try {
            itemPrototype = when {
                (args[0]).lowercase() == "armor" -> createArmor(args)
                (args[0]).lowercase() == "weapon" -> createWeapon(args)
                else -> return false
            }
        }
        catch(e: Exception) {
            return false
        }

        sender.inventory.addItem(ItemGenerator.createItem(itemPrototype))

        return true
    }


}