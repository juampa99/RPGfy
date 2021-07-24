package git.juampa99.rpgfy.item.effect.event

import git.juampa99.rpgfy.item.effect.entity.Effect
import org.bukkit.entity.Entity
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class EffectTriggerEvent(val entity: Entity, val effect: Effect): Event(), Cancellable {

    var canceled = false

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

    companion object {
        private val HANDLERS = HandlerList()

        //I just added this.
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }

    override fun isCancelled(): Boolean {
        return canceled
    }

    override fun setCancelled(cancel: Boolean) {
        canceled = cancel
    }
}