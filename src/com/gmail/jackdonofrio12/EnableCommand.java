package com.gmail.jackdonofrio12;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class EnableCommand implements CommandExecutor
{
  private JavaPlugin plugin;

  public EnableCommand(JavaPlugin plugin)
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
        if (!config.getBoolean(entityName))
        {
          config.set(entityName, true);
          sender.sendMessage(
            ChatColor.GREEN + ("Successful! " + entityName + " set to true."));
          plugin.saveConfig();
        }
        else
          sender.sendMessage(ChatColor.RED + "Already enabled.");
      }
      else
        sender.sendMessage(ChatColor.RED
          + "Invalid entity type. Check the config.yml for a complete list.");
    }
    catch (Exception e)
    {
      sender.sendMessage(ChatColor.RED
        + "Please enter an entity name. Check the config.yml file for a complete list.");
    }
    return true;
  }
}
