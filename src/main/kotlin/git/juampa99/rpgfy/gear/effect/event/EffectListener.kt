package git.juampa99.rpgfy.gear.effect.event

import git.juampa99.rpgfy.gear.builder.util.nbt.NBTEditor
import org.bukkit.Bukkit.getLogger
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class EffectListener : Listener {

    @EventHandler
    fun onRightClick(event: PlayerInteractEvent) {
        val action: Action = event.action
        val isRightClick = action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK
        val isLeftClick = action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK
        val item = event.item ?: return

        if(!isRightClick && !isLeftClick) return

        // Needs implementation
    }

}