package git.juampa99.rpgfy.command.gear

import git.juampa99.rpgfy.gear.builder.entity.GearPrototype
import git.juampa99.rpgfy.gear.builder.entity.armor.*
import git.juampa99.rpgfy.gear.builder.service.ItemBuilder
import git.juampa99.rpgfy.gear.builder.entity.weapon.Sword
import git.juampa99.rpgfy.gear.builder.entity.weapon.Weapon
import org.bukkit.Bukkit.getLogger
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SpawnGearCommand : CommandExecutor {

    private fun createWeapon(args: Array<out String>): GearPrototype {
        val itemName: String = args[2]

        val weapon: Weapon = when(args[1].lowercase()) {
            "sword" -> Sword(itemName)
            else -> throw RuntimeException()
        }

        if(args.size >= 4) weapon.damage = args[3].toDouble()
        if(args.size >= 5) weapon.attackSpeed = args[4].toDouble()

        return weapon
    }

    private fun createArmor(args: Array<out String>): GearPrototype {
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
     * weapon <type> <name> <damage?> <attack_speed?
     * */
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) return false
        if(args.size < 2) return false
        getLogger().info(args[0])

        val gearPrototype: GearPrototype

        try {
            gearPrototype = when((args[0]).lowercase()) {
                "armor" -> createArmor(args)
                "weapon" -> createWeapon(args)
                else -> return false
            }
        }
        catch(e: Exception) {
            return false
        }

        sender.inventory.addItem(ItemBuilder.createItem(gearPrototype))

        return true
    }


}