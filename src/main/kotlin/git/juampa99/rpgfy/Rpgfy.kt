package git.juampa99.rpgfy

import git.juampa99.rpgfy.command.MightySwordCommand
import git.juampa99.rpgfy.healthbar.events.HealthBarListener
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

        plugin = this

        // Register event listener
        server.pluginManager.registerEvents(HealthBarListener(), this)

        this.getCommand("mightysword")?.setExecutor(MightySwordCommand())
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