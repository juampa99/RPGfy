package git.juampa99.rpgfy.healthbar.event

import git.juampa99.rpgfy.Rpgfy
import git.juampa99.rpgfy.gear.effect.event.EffectFadeEvent
import git.juampa99.rpgfy.gear.effect.event.EffectTriggerEvent
import git.juampa99.rpgfy.healthbar.service.HealthBarService
import git.juampa99.rpgfy.healthbar.service.HealthBarService.updateHealthBar
import net.minecraft.server.v1_16_R3.MinecraftServer
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Creature
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.event.player.PlayerInteractEntityEvent

class HealthBarListener : Listener {

    @EventHandler
    fun onEntitySpawn(event: EntitySpawnEvent) {
        updateHealthBar(event.entity)
    }

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        updateHealthBar(event.entity)
    }

    @EventHandler
    fun onEntityDead(event: EntityDeathEvent) {
        if(event.entity !is Creature) return
        updateHealthBar(event.entity)
    }

    @EventHandler
    fun onPlayerTagEntity(event: PlayerInteractEntityEvent) {
        val item = event.player.inventory.getItem(event.hand)
        if(item.type == Material.NAME_TAG) updateHealthBar(event.rightClicked)
    }

    @EventHandler
    fun onEffectTrigger(event: EffectTriggerEvent) {
        HealthBarService.addEffectToEntity(event.entity, event.effect)
        updateHealthBar(event.entity)
    }

    @EventHandler
    fun onEffectFade(event: EffectFadeEvent) {
        HealthBarService.removeEffectOfEntity(event.entity, event.effect)
        updateHealthBar(event.entity)
    }

}