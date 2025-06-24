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
                player.sendMessage(MiningWorld.getInstance().getConfig().getString("messages.deleteIn5s").replace("{world}", worldName));
            }
        }, (long) (timeInSeconds - 5) * 20);

        Bukkit.getScheduler().runTaskLater(MiningWorld.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(MiningWorld.getInstance().getConfig().getString("messages.deleteIn3s").replace("{world}", worldName));
            }
        }, (long) (timeInSeconds - 3) * 20);

        Bukkit.getScheduler().runTaskLater(MiningWorld.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(MiningWorld.getInstance().getConfig().getString("messages.deleteIn2s").replace("{world}", worldName));
            }
        }, (long) (timeInSeconds - 2) * 20);

        Bukkit.getScheduler().runTaskLater(MiningWorld.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(MiningWorld.getInstance().getConfig().getString("messages.deleteIn1s").replace("{world}", worldName));
            }
        }, (long) (timeInSeconds - 1) * 20);

        Bukkit.getScheduler().runTaskLater(MiningWorld.getInstance(), () -> {
            WorldManager worldManager = new WorldManager(new FileUtils());
            worldManager.deleteWorld(worldName);
            worldManager.createWorld(worldName, String.valueOf(System.currentTimeMillis()));

            resetWorldWithWarning(worldName, timeInSeconds);

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(MiningWorld.getInstance().getConfig().getString("messages.worldReset").replace("{world}", worldName));
            }
        }, (long) timeInSeconds * 20);
    }

}
