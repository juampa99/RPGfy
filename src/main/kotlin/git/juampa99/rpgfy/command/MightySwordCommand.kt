package git.juampa99.rpgfy.command

import git.juampa99.rpgfy.items.service.ItemGenerator
import git.juampa99.rpgfy.items.util.Sword
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class MightySwordCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) return false

        val sword: ItemStack = ItemGenerator.createItem(
            Sword("Mighty Sword", 10.0,
            lore = "Much mighty, very powerful")
        )

        sender.inventory.addItem(sword)

        return true
    }


}