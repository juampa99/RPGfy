package git.juampa99.rpgfy.command.gear

import git.juampa99.rpgfy.item.custom.registry.CustomItemRegistry
import git.juampa99.rpgfy.item.effect.registry.EffectRegistry
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ListCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(args.isEmpty()) return false

        when(args[0].lowercase()) {
            "effects", "effect" ->
                sender.sendMessage("List of all effects:\n${EffectRegistry.getAllEffects()}")
            "item", "items", "customitems" ->
                sender.sendMessage("List of all custom items:\n${CustomItemRegistry.getAllItems()}")
            else -> return false
        }

        return true
    }
}