package com.gmail.jackdonofrio12;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

public class DisableCommand implements CommandExecutor
{
  private JavaPlugin plugin;

  public DisableCommand(JavaPlugin plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command command, String label,
    String[] args)
  {
    FileConfiguration config = plugin.getConfig();
    try
    {
      String entityName = args[0].toUpperCase();
      if (config.contains(entityName))
      {
        if (config.getBoolean(entityName))
        {
          config.set(entityName, false);
          sender.sendMessage(
            ChatColor.GREEN + ("Successful! " + entityName + " set to false."));
          plugin.saveConfig();

          // kill off any existing entities of this type
          int removalCount = 0;
          for (World w : plugin.getServer().getWorlds())
            for (Entity e : w.getEntities())
              if (entityName.equals(e.getType().toString()))
              {
                e.remove();
                removalCount++;
              }

          sender.sendMessage(ChatColor.GREEN + ("Removed " + removalCount
            + " living " + entityName + " from the server."));
        }
        else
          sender.sendMessage(ChatColor.RED + "Already disabled.");
      }
      else
        sender.sendMessage(ChatColor.RED
          + "Invalid entity type. Check the config.yml file for a complete list.");
    }
    catch (Exception e)
    {
      sender.sendMessage(ChatColor.RED
        + "Please enter an entity name. Check the config.yml file for a complete list.");
    }

    return true;
  }

}
