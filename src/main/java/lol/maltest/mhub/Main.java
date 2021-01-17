package lol.maltest.mhub;

import lol.maltest.mhub.cmds.GoSpawn;
import lol.maltest.mhub.events.MainEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new MainEvent(), this);
        getCommand("spawn").setExecutor(new GoSpawn());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
