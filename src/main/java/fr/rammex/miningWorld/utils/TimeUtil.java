package fr.rammex.miningWorld.utils;

import fr.rammex.miningWorld.MiningWorld;
import fr.rammex.miningWorld.world.WorldLoader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

public class TimeUtil {

    public void resetWorldWithWarning(String worldName, int timeInSeconds) {
        Bukkit.getScheduler().runTaskLater(MiningWorld.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§cAttention ! Le monde " + worldName + " sera réinitialisé dans 5 secondes !");
            }
        }, (long) (timeInSeconds - 5) * 20);

        Bukkit.getScheduler().runTaskLater(MiningWorld.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§cAttention ! Le monde " + worldName + " sera réinitialisé dans 3 secondes !");
            }
        }, (long) (timeInSeconds - 3) * 20);

        Bukkit.getScheduler().runTaskLater(MiningWorld.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§cAttention ! Le monde " + worldName + " sera réinitialisé dans 2 secondes !");
            }
        }, (long) (timeInSeconds - 2) * 20);

        Bukkit.getScheduler().runTaskLater(MiningWorld.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§cAttention ! Le monde " + worldName + " sera réinitialisé dans 1 secondes !");
            }
        }, (long) (timeInSeconds - 1) * 20);

        Bukkit.getScheduler().runTaskLater(MiningWorld.getInstance(), () -> {
            WorldManager worldManager = new WorldManager(new FileUtils());
            worldManager.deleteWorld(worldName);
            worldManager.createWorld(worldName, String.valueOf(System.currentTimeMillis()));

            resetWorldWithWarning(worldName, timeInSeconds);

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§cLe monde " + worldName + " a été réinitialisé !");
            }
        }, (long) timeInSeconds * 20);
    }

}
