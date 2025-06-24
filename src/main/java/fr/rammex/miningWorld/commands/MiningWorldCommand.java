package fr.rammex.miningWorld.commands;

import fr.rammex.miningWorld.MiningWorld;
import fr.rammex.miningWorld.utils.FileUtils;
import fr.rammex.miningWorld.utils.WorldManager;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class MiningWorldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

        Player player = (Player) sender;

        String arg1 = args.length > 0 ? args[0] : "";

        switch (arg1){
            case "create":
                if (args.length < 3) {
                    player.sendMessage("Usage: /mwpl create <worldName> <seed>");
                    return false;
                } else {
                    String worldName = args[1];
                    String seed = args[2];

                    if (player.getServer().getWorld(worldName) != null) {
                        player.sendMessage("World " + worldName + " already exists.");
                        return true;
                    }

                    WorldManager worldManager = new WorldManager(new FileUtils());
                    try {
                        worldManager.createWorld(worldName, seed);
                        player.sendMessage("World " + worldName + " has been created with seed: " + seed);
                    } catch (NumberFormatException e) {
                        player.sendMessage("Invalid seed format. Please provide a valid number.");
                        return false;
                    } catch (Exception e) {
                        player.sendMessage("An error occurred while creating the world: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
                return true;
            case "delete":
                if (args.length < 2) {
                    player.sendMessage("Usage: /mwpl delete <worldName>");
                    return false;
                } else {
                    String worldName = args[1];
                    WorldManager worldManager = new WorldManager(new FileUtils());
                    if (player.getServer().getWorld(worldName) == null) {
                        player.sendMessage("World " + worldName + " does not exist.");
                        return true;
                    }
                    worldManager.deleteWorld(worldName);
                    player.sendMessage("World " + worldName + " has been deleted.");
                }
                return true;
            case "zip":
                if (args.length < 2) {
                    player.sendMessage("Usage: /mwpl zip <worldName>");
                    return false;
                }
                String worldToZip = args[1];
                World worldToZipObj = player.getServer().getWorld(worldToZip);
                player.getServer().unloadWorld(worldToZipObj, false);
                if (worldToZipObj != null) {
                    FileUtils fileUtils = new FileUtils();
                    String zipFilePath = MiningWorld.getInstance().getServer().getWorldContainer().getPath() + File.separator + worldToZip + "_backup.zip";
                    try {
                        FileUtils.saveWorldAsZip(worldToZipObj.getWorldFolder().getPath(), zipFilePath);
                        player.sendMessage("World " + worldToZip + " has been zipped to: " + zipFilePath);
                    } catch (Exception e) {
                        player.sendMessage("An error occurred while zipping the world: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    player.sendMessage("World " + worldToZip + " does not exist.");
                }
                return true;
        }


        return false;
    }
}
