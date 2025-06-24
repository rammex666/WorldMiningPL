package fr.rammex.miningWorld.world;

import fr.rammex.miningWorld.MiningWorld;
import fr.rammex.miningWorld.utils.FileUtils;
import fr.rammex.miningWorld.utils.TimeUtil;
import fr.rammex.miningWorld.utils.WorldManager;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class WorldLoader {

    MiningWorld plugin;

    @Getter
    Map<String, Map<String, String>> worlds = new HashMap<>();
    WorldManager worldManager = new WorldManager(new FileUtils());

    public WorldLoader(MiningWorld plugin) {
        this.plugin = plugin;
    }

    public void getWorldsFromConfig(){
        if (plugin.getConfig().isConfigurationSection("worlds")) {
            for (String worldName : plugin.getConfig().getConfigurationSection("worlds").getKeys(false)) {
                String seed = plugin.getConfig().getString("worlds." + worldName + ".seed");
                String name = plugin.getConfig().getString("worlds." + worldName + ".name");
                String timeBeforeReset = plugin.getConfig().getString("worlds." + worldName + ".timeBeforeReset");

                Map<String, String> worldData = new HashMap<>();
                worldData.put("seed", seed);
                worldData.put("name", name);
                worldData.put("timeBeforeReset", timeBeforeReset);

                worlds.put(worldName, worldData);
                System.out.println("World loaded from config: " + worldName + " with seed: " + seed);
            }
        } else {
            plugin.getLogger().info("No worlds found in the config.");
        }
    }

    public void loadWorlds() {
        for (Map.Entry<String, Map<String, String>> entry : worlds.entrySet()) {
            Map<String, String> worldData = entry.getValue();

            String name = worldData.get("name");
            String seedString = worldData.get("seed");
            long seed = seedString != null ? Long.parseLong(seedString) : 0;

            plugin.getLogger().info("Loading world: " + name + " with seed: " + seed);

            this.worldManager.createWorld(name, String.valueOf(seed));
            TimeUtil timeUtil = new TimeUtil();
            timeUtil.resetWorldWithWarning(
                    name,
                    Integer.parseInt(worldData.getOrDefault("timeBeforeReset", "300"))
            );
        }
    }





}
