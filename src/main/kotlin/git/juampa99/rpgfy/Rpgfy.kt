package git.juampa99.rpgfy

import git.juampa99.rpgfy.command.gear.SpawnGearCommand
import git.juampa99.rpgfy.droptable.listener.DroptableListener
import git.juampa99.rpgfy.droptable.service.DroptableService
import git.juampa99.rpgfy.gear.effect.entity.impl.weapon.SlownessEffect
import git.juampa99.rpgfy.gear.effect.listener.EffectListener
import git.juampa99.rpgfy.gear.effect.registry.EffectRegistry
import git.juampa99.rpgfy.healthbar.listener.HealthBarListener
import git.juampa99.rpgfy.utils.ymlparser.YamlParser
import git.juampa99.rpgfy.droptable.entity.DropTableEntry
import git.juampa99.rpgfy.droptable.builder.DroptableEntryBuilder
import git.juampa99.rpgfy.gear.builder.entity.Item
import git.juampa99.rpgfy.gear.custom.builder.ItemYamlBuilder
import git.juampa99.rpgfy.gear.custom.service.CustomItemService
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.plugin.java.JavaPlugin

class Rpgfy : JavaPlugin() {

    // Used to retrieve the main instance
    internal companion object Instance {
        var plugin: Rpgfy? = null
    }

    override fun onEnable() {
        logger.info("Loading Rpgfy...")
        plugin = this

        saveDefaultConfig()

        // Custom items MUST be loaded before droptable, since
        // it depends on custom items
        loadCustomItems()
        loadDroptable()

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
        server.pluginManager.registerEvents(DroptableListener(), this)
    }

    private fun registerCommands() {
        this.getCommand("spawngear")?.setExecutor(SpawnGearCommand())
    }

    private fun loadCustomItems() {
        val items = YamlParser.parseYmlConfig("custom_items.yml",
            "", ItemYamlBuilder).filterIsInstance<Item>()

        logger.info(items.toString())

        CustomItemService.storeItems(items)
    }

    private fun loadDroptable() {
        // This could be done taking entries of the yaml file
        val entities = listOf("zombie", "creeper", "skeleton", "enderman")
        val droptable = mutableMapOf<String, List<DropTableEntry>>()

        for(entity in entities) {
            droptable[entity] =
                YamlParser.parseYmlConfig("droptable.yml",
                    "$entity.drop", DroptableEntryBuilder
                ).filterIsInstance<DropTableEntry>()
        }

        DroptableService.storeDrops(droptable)
    }

    /*
    *  Logs when a player executes a command
    * */
    @EventHandler
    fun onCommandPreprocess(e: PlayerCommandPreprocessEvent?) {
        getLogger().info("Command ${e?.message} called by ${e?.player?.name}")
    }

}