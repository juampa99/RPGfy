package git.juampa99.rpgfy.droptable.listener

import git.juampa99.rpgfy.droptable.service.DroptableService
import git.juampa99.rpgfy.utils.string.toSlug
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class DroptableListener : Listener {

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val droppedItems = DroptableService.rollDrops(event.entity.type.toString())

        for(item in droppedItems)
            event.entity.world.dropItem(event.entity.location, item)
    }

}