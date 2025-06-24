package fr.rammex.miningWorld;

import fr.rammex.miningWorld.commands.MiningWorldCommand;
import fr.rammex.miningWorld.world.WorldLoader;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiningWorld extends JavaPlugin {

    @Getter
    static MiningWorld instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;

        getCommand("mwpl").setExecutor(new MiningWorldCommand());

        // WORLD MANAGER
        WorldLoader worldLoader = new WorldLoader(this);
        worldLoader.getWorldsFromConfig();
        worldLoader.loadWorlds();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
