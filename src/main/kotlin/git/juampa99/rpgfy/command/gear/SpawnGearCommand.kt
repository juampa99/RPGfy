package git.juampa99.rpgfy.command.gear

import git.juampa99.rpgfy.item.builder.entity.GearPrototype
import git.juampa99.rpgfy.item.builder.entity.armor.*
import git.juampa99.rpgfy.item.builder.service.ItemBuilder
import git.juampa99.rpgfy.item.builder.entity.weapon.Sword
import git.juampa99.rpgfy.item.builder.entity.weapon.Weapon
import git.juampa99.rpgfy.item.custom.registry.CustomItemRegister
import git.juampa99.rpgfy.item.effect.entity.ArmorEffect
import git.juampa99.rpgfy.item.effect.entity.WeaponEffect
import git.juampa99.rpgfy.item.effect.entity.impl.weapon.SlownessEffect
import org.bukkit.Bukkit.getLogger
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SpawnGearCommand : CommandExecutor {

    private fun getWeaponEffect(effectName: String): WeaponEffect {
        when(effectName) {
            SlownessEffect.name -> return SlownessEffect
            else -> throw RuntimeException("No effect named $effectName exists")
        }
    }

    private fun getArmorEffect(effectName: String): ArmorEffect {
        when(effectName) {
            else -> throw RuntimeException("No effect named $effectName exists")
        }
    }

    private fun createWeapon(args: Array<out String>): Weapon {
        val itemName: String = args[2]

        val weapon: Weapon = when(args[1].lowercase()) {
            "sword" -> Sword(itemName)
            else -> throw RuntimeException()
        }

        if(args.size >= 4) weapon.damage = args[3].toDouble()
        if(args.size >= 5) weapon.attackSpeed = args[4].toDouble()
        if(args.size >= 7) {
            val effects: MutableList<Pair<WeaponEffect, Int>> = mutableListOf()
            var i = 5

            while(i < args.size) {
                effects.add(Pair(getWeaponEffect(args[i]), args[i+1].toInt()))
                i += 2
            }

            weapon.effects = effects
        }

        return weapon
    }

    private fun createArmor(args: Array<out String>): ArmorPiece {
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
        if(args.size >= 7) {
            val effects: MutableList<Pair<ArmorEffect, Int>> = mutableListOf()
            var i = 5

            while(i < args.size) {
                effects.add(Pair(getArmorEffect(args[i]), args[i+1].toInt()))
                i += 2
            }

            armor.effects = effects
        }

        return armor
    }

    /**
     * armor <type> <name> <armor?> <armor_toughness?> <effect1?> <value1?> ... <effectN?> <valueN?>
     * weapon <type> <name> <damage?> <attack_speed? <effect1?> <value1?> ... <effectN?> <valueN?>
     * */
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) return false
        if(!CustomItemRegister.itemExists(args[0]) && args.size < 2) return false
        if(args[0].lowercase() == "test") {
            val testSword = Sword(
                "Test Sword",
                0.1, 0.5, effects = listOf(Pair(SlownessEffect, 3))
            )

            sender.inventory.addItem(ItemBuilder.createItem(testSword))

            return true
        }

        try {

            val gearPiece: GearPrototype = if(CustomItemRegister.itemExists(args[0])) {
                (CustomItemRegister.getItem(args[0]) ?: return false) as GearPrototype
            } else {
                when((args[0]).lowercase()) {
                    "armor" -> createArmor(args)
                    "weapon" -> createWeapon(args)
                    else -> return false
                }

            }

            sender.inventory.addItem(ItemBuilder.createItem(gearPiece))
        }
        catch(e: Exception) {
            getLogger().warning(e.toString())
            return false
        }

        return true
    }


}