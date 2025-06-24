package fr.rammex.miningWorld.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldManager {

    private final FileUtils fileUtils;

    public WorldManager(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }


    public void createWorld(String worldName, String seed) {

        WorldCreator creator = new WorldCreator(worldName);
        creator.seed(Long.parseLong(seed));

        Bukkit.createWorld(creator);

        World world = Bukkit.getWorld(worldName);
        assert world != null;
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.DO_FIRE_TICK, false);

        System.out.println("Création du monde " + worldName + " avec le seed " + seed + " réussie.");
    }

    public void deleteWorld(String worldName) {
        if (Bukkit.getWorld(worldName) != null) {
            World world = Bukkit.getWorld(worldName);
            Bukkit.unloadWorld(worldName, false);
            Bukkit.getServer().getWorlds().remove(Bukkit.getWorld(worldName));
            this.fileUtils.deleteWorld(world.getWorldFolder());
            System.out.println("Le monde " + worldName + " a été supprimé avec succès.");
        } else {
            System.out.println("Le monde " + worldName + " n'existe pas.");
        }
    }


}
