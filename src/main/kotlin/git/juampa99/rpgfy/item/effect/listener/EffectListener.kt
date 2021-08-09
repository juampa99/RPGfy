package git.juampa99.rpgfy.item.effect.listener

import git.juampa99.rpgfy.item.effect.service.EffectService
import org.bukkit.Bukkit.getLogger
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityTargetLivingEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

class EffectListener : Listener {

    // This event will be used for right-click skills, none implemented yet
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

        // Player hit a Mob
        if(damager is Player && damaged is LivingEntity) {
            val itemInHand = damager.inventory.getItem(EquipmentSlot.HAND)

            if(EffectService.hasEffects(itemInHand))
                EffectService.triggerEffects(itemInHand, damager, damaged)
        }
        // Mob hit Player
        else if(damaged is Player && damager is LivingEntity) {
            val player = damaged
            val mob = damager

            EffectService.triggerAllArmorEffects(player, mob)
        }
    }

}