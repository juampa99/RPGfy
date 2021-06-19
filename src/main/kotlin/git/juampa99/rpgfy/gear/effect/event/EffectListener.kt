package git.juampa99.rpgfy.gear.effect.event

import git.juampa99.rpgfy.gear.builder.util.nbt.NBTEditor
import git.juampa99.rpgfy.gear.effect.registry.EffectManager
import org.bukkit.Bukkit.getLogger
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

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

    @EventHandler
    fun onEntityDamage(event: EntityDamageByEntityEvent) {
        val damager = event.damager
        val damaged = event.entity
        if(damager is Player && damaged is LivingEntity) {
            val itemInHand = damager.inventory.getItem(EquipmentSlot.HAND)
            if(EffectManager.hasEffects(itemInHand))
                EffectManager.triggerEffects(itemInHand, damager, damaged)
        }
    }

}