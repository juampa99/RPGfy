package git.juampa99.rpgfy

import git.juampa99.rpgfy.command.gear.SpawnGearCommand
import git.juampa99.rpgfy.gear.effect.entity.impl.weapon.SlownessEffect
import git.juampa99.rpgfy.gear.effect.listener.EffectListener
import git.juampa99.rpgfy.gear.effect.registry.EffectRegistry
import git.juampa99.rpgfy.healthbar.listener.HealthBarListener
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

        registerEffects()
        registerEvents()
        registerCommands()
    }

    private fun registerEffects() {
        // Register effects this should be done somewhere else
        EffectRegistry.register(SlownessEffect)
    }

    private fun registerEvents() {
        server.pluginManager.registerEvents(HealthBarListener(), this)
        server.pluginManager.registerEvents(EffectListener(), this)
    }

    private fun registerCommands() {
        this.getCommand("spawngear")?.setExecutor(SpawnGearCommand())
    }

    /*
    *  Logs when a player executes a command
    * */
    @EventHandler
    fun onCommandPreprocess(e: PlayerCommandPreprocessEvent?) {
        getLogger().info("Command ${e?.message} called by ${e?.player?.name}")
    }

}