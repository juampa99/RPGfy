package git.juampa99.rpgfy

import git.juampa99.rpgfy.command.gear.SpawnGearCommand
import git.juampa99.rpgfy.gear.effect.entity.SlownessEffect
import git.juampa99.rpgfy.gear.effect.event.EffectListener
import git.juampa99.rpgfy.gear.effect.registry.EffectManager
import git.juampa99.rpgfy.healthbar.event.HealthBarListener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.plugin.java.JavaPlugin

class Rpgfy : JavaPlugin() {

    // Used to retrieve the main instance
    internal companion object instance {
        var plugin: Rpgfy? = null
    }

    override fun onEnable() {
        logger.info("Loading Rpgfy...")

        saveDefaultConfig()

        plugin = this

        // Register effects this should be done somewhere else
        EffectManager.register(SlownessEffect)

        // Register event listener
        server.pluginManager.registerEvents(HealthBarListener(), this)
        server.pluginManager.registerEvents(EffectListener(), this)

        // Register commands
        this.getCommand("spawngear")?.setExecutor(SpawnGearCommand())
    }

    override fun onDisable() {
        logger.info("Disabling Rpgfy...")
        super.onDisable()
    }

    /*
    *  Logs when a player executes a command
    * */
    @EventHandler
    fun onCommandPreprocess(e: PlayerCommandPreprocessEvent?) {
        getLogger().info("Command ${e?.message} called by ${e?.player?.name}")
    }

}