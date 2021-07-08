package git.juampa99.rpgfy.droptable.listener

import git.juampa99.rpgfy.droptable.service.DroptableService
import git.juampa99.rpgfy.droptable.ymlparser.entity.DropTableEntry
import git.juampa99.rpgfy.gear.builder.service.ItemBuilder
import git.juampa99.rpgfy.utils.string.toSlug
import org.bukkit.Bukkit.getLogger
import org.bukkit.Material
import org.bukkit.entity.EntityCategory
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class DroptableListener : Listener {

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val dropTable: List<DropTableEntry>
            = DroptableService.getDropsFromEntity(event.entity.type.toString())

        for(item in dropTable) {
            // Min positive decimal for double values
            val minRoll = 0.0000000000000001
            val roll = Random.nextDouble(minRoll, 100.0)

            // Drop item if roll is between 0 and drop chance
            if(roll <= item.dropChance) {
                val itemSlug = item.itemName.toSlug()
                val itemStack = ItemStack(Material.valueOf(itemSlug))
                val minQuantity = item.minQuantity
                val maxQuantity = item.maxQuantity

                itemStack.amount = Random.nextInt(minQuantity, maxQuantity)

                event.entity.world.dropItem(event.entity.location, itemStack)
            }

        }

    }

}