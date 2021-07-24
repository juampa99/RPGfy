package git.juampa99.rpgfy

import git.juampa99.rpgfy.command.gear.SpawnGearCommand
import git.juampa99.rpgfy.droptable.listener.DroptableListener
import git.juampa99.rpgfy.droptable.service.DroptableService
import git.juampa99.rpgfy.item.effect.entity.impl.weapon.SlownessEffect
import git.juampa99.rpgfy.item.effect.listener.EffectListener
import git.juampa99.rpgfy.item.effect.registry.EffectRegistry
import git.juampa99.rpgfy.healthbar.listener.HealthBarListener
import git.juampa99.rpgfy.utils.ymlparser.YamlParser
import git.juampa99.rpgfy.droptable.entity.DropTableEntry
import git.juampa99.rpgfy.droptable.builder.DroptableEntryBuilder
import git.juampa99.rpgfy.item.builder.entity.Item
import git.juampa99.rpgfy.item.custom.builder.ItemYamlBuilder
import git.juampa99.rpgfy.item.custom.registry.CustomItemRegister
import git.juampa99.rpgfy.item.effect.entity.impl.weapon.PoisonEffect
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

        // Registers must be done before static loading
        registerEffects()
        registerEvents()
        registerCommands()

        // Custom items MUST be loaded before droptable, since
        // it depends on custom items
        loadCustomItems()
        loadDroptable()
    }

    private fun registerEffects() {
        logger.info("Registering effects..")

        // Register effects this should be done somewhere else
        EffectRegistry.register(SlownessEffect)
        EffectRegistry.register(PoisonEffect)
    }

    private fun registerEvents() {
        logger.info("Registering events..")

        server.pluginManager.registerEvents(HealthBarListener(), this)
        server.pluginManager.registerEvents(EffectListener(), this)
        server.pluginManager.registerEvents(DroptableListener(), this)
    }

    private fun registerCommands() {
        logger.info("Registering commands..")

        this.getCommand("spawngear")?.setExecutor(SpawnGearCommand())
    }

    private fun loadCustomItems() {
        val filePath = "custom_items.yml"
        logger.info("Loading custom items from $filePath..")

        val items = YamlParser.parseYmlConfig(filePath,
            "", ItemYamlBuilder).filterIsInstance<Item>()

        logger.info(items.toString())

        CustomItemRegister.registerItems(items)
    }

    private fun loadDroptable() {
        val filePath = "droptable.yml"
        logger.info("Loading droptable from $filePath..")

        // This could be done taking entries of the yaml file
        val entities = listOf("zombie", "creeper", "skeleton", "enderman")
        val droptable = mutableMapOf<String, List<DropTableEntry>>()

        for(entity in entities) {
            droptable[entity] =
                YamlParser.parseYmlConfig(filePath,
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